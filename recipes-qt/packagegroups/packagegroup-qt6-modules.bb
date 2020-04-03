DESCRIPTION = "Qt6 modules"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

RDEPENDS_${PN} += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'qtx11extras', '', d)} \
    qtbase \
    qtconnectivity \
    qtdeclarative \
    qtdeclarative-tools \
    qtgraphicaleffects \
    qtimageformats \
    qtnetworkauth \
    qtquickcontrols2 \
    qtserialport \
    qtsvg \
    qttools \
    qttools-tools \
    qtwebchannel \
    qtwebsockets \
"
