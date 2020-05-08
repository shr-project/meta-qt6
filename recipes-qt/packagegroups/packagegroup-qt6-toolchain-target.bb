DESCRIPTION = "Qt6 development packages"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"


RDEPENDS_${PN} += "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland-dev', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'qtx11extras-dev', '', d)} \
    qtbase-dev \
    qtdeclarative-dev \
    qtgraphicaleffects-dev \
    qtimageformats-dev \
    qtnetworkauth-dev \
    qtquickcontrols2-dev \
    qtserialport-dev \
    qtsvg-dev \
    qttools-dev \
    qtwebchannel-dev \
    qtwebsockets-dev \
    qt5compat-dev \
"
