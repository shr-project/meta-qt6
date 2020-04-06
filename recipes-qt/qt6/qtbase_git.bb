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
    file://0001-Add-linux-oe-g-platform.patch \
    file://0002-qlibraryinfo-allow-to-set-qt.conf-from-the-outside-u.patch \
    file://0003-Sysrootify-qmake.patch \
    file://0004-qtbase-allow-paths-outside-of-prefix.patch \
    file://0005-Allow-build-without-opengl.patch \
"

DEPENDS += "\
    freetype \
    pcre2 \
"

PACKAGECONFIG_class-native ?= "gui widgets png dbus"
PACKAGECONFIG_class-nativesdk ?= "gui widgets png dbus"
PACKAGECONFIG ?= "\
    ${PACKAGECONFIG_DEFAULT} \
    ${PACKAGECONFIG_GRAPHICS} \
    ${PACKAGECONFIG_X11} \
    ${PACKAGECONFIG_KDE} \
    ${PACKAGECONFIG_FONTS} \
    ${PACKAGECONFIG_SYSTEM} \
    ${PACKAGECONFIG_DISTRO} \
"

PACKAGECONFIG_GRAPHICS ?= "\
    ${@bb.utils.filter('DISTRO_FEATURES', 'vulkan', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', \
        bb.utils.contains('DISTRO_FEATURES', 'x11', 'opengl', 'gles2', d), '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'directfb', 'directfb', '', d)} \
"
PACKAGECONFIG_X11 ?= "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xcb xkbcommon glib', '', d)}"
PACKAGECONFIG_KDE ?= "${@bb.utils.contains('DISTRO_FEATURES', 'kde', 'sm cups kms gbm sql-sqlite', '', d)}"
PACKAGECONFIG_FONTS ?= ""
PACKAGECONFIG_SYSTEM ?= ""
PACKAGECONFIG_DISTRO ?= ""
PACKAGECONFIG_DEFAULT ?= "accessibility dbus udev gui widgets icu openssl  \
    jpeg png dbus libinput fontconfig harfbuzz \
    ${@bb.utils.contains('SELECTED_OPTIMIZATION', '-Os', 'optimize-size ltcg', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'qt5-static', 'static', '', d)} \
"
# Build type: Debug, Release, MinSizeRel, RelWithDebInfo
BUILD_TYPE ?= "Release"
# OpenSSL linking mode: runtime, linked
OPENSSL_LINKING_MODE ?= "runtime"

PACKAGECONFIG[cups] = "-DFEATURE_cups=ON,-DFEATURE_cups=OFF,cups"
PACKAGECONFIG[dbus] = "-DFEATURE_dbus=ON,-DFEATURE_dbus=OFF,dbus"
PACKAGECONFIG[udev] = "-DFEATURE_libudev=ON,-DFEATURE_libudev=OFF,udev"
PACKAGECONFIG[zstd] = "-DFEATURE_zstd=ON,-DFEATURE_zstd=OFF,zstd"

# corelib
PACKAGECONFIG[glib] = "-DFEATURE_glib=ON,-DFEATURE_glib=OFF,glib-2.0"
PACKAGECONFIG[iconv] = "-DFEATURE_iconv=ON,-DFEATURE_iconv=OFF,virtual/libiconv"
PACKAGECONFIG[icu] = "-DFEATURE_icu=ON,-DFEATURE_icu=OFF,icu"
PACKAGECONFIG[journald] = "-DFEATURE_journald=ON,-DFEATURE_journald=OFF,systemd"
PACKAGECONFIG[lttng] = "-DFEATURE_lttng=ON,-DFEATURE_lttng=OFF,lttng-ust"

# gui
PACKAGECONFIG[gui] = "-DFEATURE_gui=ON,-DFEATURE_gui=OFF"
PACKAGECONFIG[accessibility] = "-DFEATURE_accessibility=ON,-DFEATURE_accessibility=OFF,at-spi2-atk"
PACKAGECONFIG[directfb] = "-DFEATURE_directfb=ON,-DFEATURE_directfb=OFF,directfb"
PACKAGECONFIG[fontconfig] = "-DFEATURE_fontconfig=ON,-DFEATURE_fontconfig=OFF,fontconfig"
PACKAGECONFIG[gbm] = "-DFEATURE_gbm=ON,-DFEATURE_gbm=OFF,virtual/libgbm"
PACKAGECONFIG[gles2] = "-DFEATURE_opengles2=ON,-DFEATURE_opengles2=OFF,virtual/libgles2 virtual/egl"
PACKAGECONFIG[harfbuzz] = "-DFEATURE_harfbuzz=ON,-DFEATURE_harfbuzz=OFF,harfbuzz"
PACKAGECONFIG[jpeg] = "-DFEATURE_jpeg=ON,-DFEATURE_jpeg=OFF,jpeg"
PACKAGECONFIG[kms] = "-DFEATURE_kms=ON,-DFEATURE_kms=OFF,drm virtual/egl"
PACKAGECONFIG[libinput] = "-DFEATURE_libinput=ON,-DFEATURE_libinput=OFF,libinput"
PACKAGECONFIG[mtdev] = "-DFEATURE_mtdev=ON,-DFEATURE_mtdev=OFF,mtdev"
PACKAGECONFIG[opengl] = "-DFEATURE_opengl_desktop=ON,-DFEATURE_opengl_desktop=OFF,virtual/libgl"
PACKAGECONFIG[png] = "-DFEATURE_png=ON,-DFEATURE_png=OFF,libpng"
PACKAGECONFIG[tslib] = "-DFEATURE_tslib=ON,-DFEATURE_tslib=OFF,tslib"
PACKAGECONFIG[vulkan] = "-DFEATURE_vulkan=ON,-DFEATURE_vulkan=OFF,vulkan-headers vulkan-loader"
PACKAGECONFIG[xcb] = "-DFEATURE_xcb=ON,-DFEATURE_xcb=OFF,libxcb xcb-util-wm xcb-util-image xcb-util-keysyms xcb-util-renderutil"
PACKAGECONFIG[xkbcommon] = "-DFEATURE_xkbcommon=ON,-DFEATURE_xkbcommon=OFF,libxkbcommon,xkeyboard-config"

# widgets
PACKAGECONFIG[widgets] = "-DFEATURE_widgets=ON,-DFEATURE_widgets=OFF"
PACKAGECONFIG[gtk] = "-DFEATURE_gtk3=ON,-DFEATUER_gtk3=OFF,gtk+3"

# network
PACKAGECONFIG[gssapi] = "-DFEATURE_gssapi=ON,-DFEATURE_gssapi=OFF,krb5"
PACKAGECONFIG[libproxy] = "-DFEATURE_libproxy=ON,-DFEATURE_libproxy=OFF,libproxy"
PACKAGECONFIG[openssl] = "-DFEATURE_openssl_${OPENSSL_LINKING_MODE}=ON,-DFEATURE_openssl=OFF,openssl,libssl"

# sqldrivers
PACKAGECONFIG[sql-mysql] = "-DFEATURE_sql_mysql=ON,-DFEATURE_sql_mysql=OFF,mysql5"
PACKAGECONFIG[sql-psql] = "-DFEATURE_sql_psql=ON,-DFEATURE_sql-psql=OFF,postgresql"
PACKAGECONFIG[sql-sqlite] = "-DFEATURE_sql_sqlite=ON,-DFEATURE_sql_sqlite=OFF,sqlite3"

EXTRA_OECMAKE += "\
    -DCMAKE_BUILD_TYPE=${BUILD_TYPE} \
"

EXTRA_OECMAKE_append_class-target = "\
    -DCMAKE_SKIP_RPATH=ON \
"

SYSROOT_DIRS += "${prefix}/mkspecs"

do_install_append() {
    sed -i ${D}${libdir}/cmake/Qt6BuildInternals/QtBuildInternalsExtra.cmake \
        -e '/QT_SOURCE_TREE/,+2d' \
        -e '/CMAKE_INSTALL_PREFIX/,+2d'

    if [ ! -e ${D}/${QT6_INSTALL_MKSPECSDIR}/oe-device-extra.pri ]; then
        touch ${D}/${QT6_INSTALL_MKSPECSDIR}/oe-device-extra.pri
    fi

    # confligs with qttools module cmake files
    rm -rf ${D}${libdir}/cmake/Qt6Tools
}

FILES_${PN}-tools += "\
    ${QT6_INSTALL_LIBEXECDIR}/syncqt.pl \
"

BBCLASSEXTEND =+ "native nativesdk"

SRCREV = "430232e44d5e6c7d7fd7ca3ebebb06d74e21ce2f"
