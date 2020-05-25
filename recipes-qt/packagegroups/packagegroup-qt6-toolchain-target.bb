DESCRIPTION = "Qt6 development packages"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"


RDEPENDS_${PN} += "\
    qt5compat-dev \
    qtbase-dev \
    qtcoap-dev \
    qtdeclarative-dev \
    qtgraphicaleffects-dev \
    qtimageformats-dev \
    qtmqtt-dev \
    qtnetworkauth-dev \
    qtquick3d-dev \
    qtquickcontrols2-dev \
    qtserialport-dev \
    qtshadertools-dev \
    qtsvg-dev \
    qttools-dev \
    qttranslations-dev \
    qtvirtualkeyboard-dev \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland-dev', '', d)} \
    qtwebchannel-dev \
    qtwebsockets-dev \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'qtx11extras-dev', '', d)} \
"
