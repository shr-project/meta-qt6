LICENSE = "GFDL-1.3 & BSD & ( GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 ) & ( GPL-2.0+ | LGPL-3.0 ) | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://LICENSE.QT-LICENSE-AGREEMENT-4.0;md5=948f8877345cd66106f11031977a4625 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6.inc
include recipes-qt/qt6/qt6-git.inc

SRC_URI += "\
    file://0001-qtbase-don-t-use-neon-flags-on-arm64.patch \
    file://0002-qtbase-use-syncqt.pl-from-QT_HOST_PATH.patch \
    file://0003-Add-additional-include-path-for-DRM.patch \
"

DEPENDS = "\
    pcre2 \
    freetype \
"

PACKAGECONFIG_class-native ?= "gui widgets dbus"
PACKAGECONFIG_class-nativesdk ?= "gui widgets dbus"
PACKAGECONFIG_class-target ?= "\
    ${PACKAGECONFIG_DEFAULT} \
    ${PACKAGECONFIG_GL} \
    ${PACKAGECONFIG_FB} \
    ${PACKAGECONFIG_X11} \
    ${PACKAGECONFIG_KDE} \
    ${PACKAGECONFIG_FONTS} \
    ${PACKAGECONFIG_SYSTEM} \
    ${PACKAGECONFIG_DISTRO} \
"

PACKAGECONFIG_GL ?= "${@bb.utils.contains('DISTRO_FEATURES', 'x11 opengl', 'opengl', \
                        bb.utils.contains('DISTRO_FEATURES', 'opengl', 'eglfs gles2', '', d), d)}"
PACKAGECONFIG_FB ?= "${@bb.utils.contains('DISTRO_FEATURES', 'directfb', 'directfb', '', d)}"
PACKAGECONFIG_X11 ?= "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xcb xcb-xinput glib xkb xkbcommon', '', d)}"
PACKAGECONFIG_KDE ?= "${@bb.utils.contains('DISTRO_FEATURES', 'kde', 'sm cups fontconfig kms gbm libinput sql-sqlite openssl', '', d)}"
PACKAGECONFIG_FONTS ?= ""
PACKAGECONFIG_SYSTEM ?= "jpeg libpng zlib dbus"
PACKAGECONFIG_DISTRO ?= ""
PACKAGECONFIG_DEFAULT ?= "accessibility dbus udev evdev gui widgets tools libs icu openssl  \
    ${@bb.utils.contains('SELECTED_OPTIMIZATION', '-Os', 'optimize-size ltcg', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'qt5-static', 'static', '', d)} \
"
# Build type: Debug, Release, MinSizeRel, RelWithDebInfo
BUILD_TYPE ?= "Release"
# OpenSSL linking mode: runtime, linked
OPENSSL_LINKING_MODE ?= "runtime"

PACKAGECONFIG[gui] = "-DFEATURE_gui=ON,-DFEATURE_gui=OFF,libpng"
PACKAGECONFIG[opengl] = "-DFEATURE_opengl=ON,-DFEATURE_opengl=OFF,virtual/libgl"
PACKAGECONFIG[gles2] = "-DFEATURE_opengles2=ON,-DFEATURE_opengles2=OFF,virtual/libgles2 virtual/egl"
PACKAGECONFIG[iconv] = "-DFEATURE_iconv=ON,-DFEATURE_iconv=OFF,virtual/libiconv"
PACKAGECONFIG[icu] = "-DFEATURE_icu=ON,-DFEATURE_icu=OFF,icu"
PACKAGECONFIG[widgets] = "-DFEATURE_widgets=ON,-DFEATURE_widgets=OFF"
PACKAGECONFIG[xcb] = "-DFEATURE_xcb=ON,-DFEATURE_xcb=OFF,libxcb libxkbcommon xcb-util-wm xcb-util-image xcb-util-keysyms xcb-util-renderutil libxext"
PACKAGECONFIG[dbus] = "-DFEATURE_dbus=ON,-DFEATURE_dbus=OFF,dbus"
PACKAGECONFIG[openssl] = "-DFEATURE_openssl${OPENSSL_LINKING_MODE}=ON,-DFEATURE_openssl=OFF,openssl,libssl"
PACKAGECONFIG[sql-sqlite] = "-DFEATURE_sql_sqlite=ON,-DFEATURE_sql_sqlite=OFF,sqlite3"
PACKAGECONFIG[accessibility] = "-DFEATURE_accessibility=ON,-DFEATURE_accessibility=OFF,at-spi2-atk"

EXTRA_OECMAKE += " \
    -DCMAKE_BUILD_TYPE=${BUILD_TYPE} \
"

SYSROOT_DIRS += "${exec_prefix}/mkspecs"

do_install_append() {
    sed -i ${D}${libdir}/cmake/Qt6BuildInternals/QtBuildInternalsExtra.cmake \
        -e '/QT_SOURCE_TREE/,+2d' \
        -e '/CMAKE_INSTALL_PREFIX/,+2d'

    # confligs with qttools module cmake files
    rm -rf ${D}${libdir}/cmake/Qt6Tools
}

BBCLASSEXTEND =+ "native nativesdk"

SRCREV = "b29cb7889ac5b3a7b4b515971f462f65b7db7ec4"
