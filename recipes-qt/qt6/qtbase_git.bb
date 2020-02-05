LICENSE = "GFDL-1.3 & BSD & ( GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 ) & ( GPL-2.0+ | LGPL-3.0 ) | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://LICENSE.QT-LICENSE-AGREEMENT;md5=c8b6dd132d52c6e5a545df07a4e3e283 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6.inc
include recipes-qt/qt6/qt6-git.inc

SRC_URI += "\
    file://0001-Add-additional-include-path-for-DRM.patch \
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
                        bb.utils.contains('DISTRO_FEATURES', 'opengl', 'gles2', '', d), d)}"
PACKAGECONFIG_FB ?= "${@bb.utils.contains('DISTRO_FEATURES', 'directfb', 'directfb', '', d)}"
PACKAGECONFIG_X11 ?= "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xcb xcb-xinput glib xkb xkbcommon', '', d)}"
PACKAGECONFIG_KDE ?= "${@bb.utils.contains('DISTRO_FEATURES', 'kde', 'sm cups fontconfig kms gbm libinput sql-sqlite openssl', '', d)}"
PACKAGECONFIG_FONTS ?= ""
PACKAGECONFIG_SYSTEM ?= ""
PACKAGECONFIG_DISTRO ?= ""
PACKAGECONFIG_DEFAULT ?= "accessibility dbus udev gui widgets icu openssl  \
    jpeg libpng dbus \
    ${@bb.utils.contains('SELECTED_OPTIMIZATION', '-Os', 'optimize-size ltcg', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'qt5-static', 'static', '', d)} \
"
# Build type: Debug, Release, MinSizeRel, RelWithDebInfo
BUILD_TYPE ?= "Release"
# OpenSSL linking mode: runtime, linked
OPENSSL_LINKING_MODE ?= "runtime"

PACKAGECONFIG[gui] = "-DFEATURE_gui=ON,-DFEATURE_gui=OFF,libpng"
PACKAGECONFIG[opengl] = "-DFEATURE_opengl_desktop=ON,-DFEATURE_opengl_desktop=OFF,virtual/libgl"
PACKAGECONFIG[gles2] = "-DFEATURE_opengles2=ON,-DFEATURE_opengles2=OFF,virtual/libgles2 virtual/egl"
PACKAGECONFIG[iconv] = "-DFEATURE_iconv=ON,-DFEATURE_iconv=OFF,virtual/libiconv"
PACKAGECONFIG[icu] = "-DFEATURE_icu=ON,-DFEATURE_icu=OFF,icu"
PACKAGECONFIG[widgets] = "-DFEATURE_widgets=ON,-DFEATURE_widgets=OFF"
PACKAGECONFIG[xcb] = "-DFEATURE_xcb=ON,-DFEATURE_xcb=OFF,libxcb libxkbcommon xcb-util-wm xcb-util-image xcb-util-keysyms xcb-util-renderutil libxext"
PACKAGECONFIG[dbus] = "-DFEATURE_dbus=ON,-DFEATURE_dbus=OFF,dbus"
PACKAGECONFIG[openssl] = "-DFEATURE_openssl_${OPENSSL_LINKING_MODE}=ON,-DFEATURE_openssl=OFF,openssl,libssl"
PACKAGECONFIG[sql-sqlite] = "-DFEATURE_sql_sqlite=ON,-DFEATURE_sql_sqlite=OFF,sqlite3"
PACKAGECONFIG[accessibility] = "-DFEATURE_accessibility=ON,-DFEATURE_accessibility=OFF,at-spi2-atk"
PACKAGECONFIG[jpeg] = "-DFEATURE_jpeg=ON,-DFEATURE_jpeg=OFF,jpeg"
PACKAGECONFIG[libpng] = "-DFEATURE_libpng=ON,-DFEATURE_libpng=OFF,libpng"
PACKAGECONFIG[udev] = "-DFEATURE_libudev=ON,-DFEATURE_libudev=OFF,udev"

EXTRA_OECMAKE += " \
    -DCMAKE_BUILD_TYPE=${BUILD_TYPE} \
"

SYSROOT_DIRS += "${prefix}/mkspecs"

do_install_append() {
    sed -i ${D}${libdir}/cmake/Qt6BuildInternals/QtBuildInternalsExtra.cmake \
        -e '/QT_SOURCE_TREE/,+2d' \
        -e '/CMAKE_INSTALL_PREFIX/,+2d'

    # confligs with qttools module cmake files
    rm -rf ${D}${libdir}/cmake/Qt6Tools
}

FILES_${PN}-tools += "\
    ${QT6_INSTALL_LIBEXECDIR}/syncqt.pl \
"

BBCLASSEXTEND =+ "native nativesdk"

SRCREV = "0f8039140c9e32cb1643eabd4539c53f0e5c1482"
