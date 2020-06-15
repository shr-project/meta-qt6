DESCRIPTION = "Qt6 modules"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

RDEPENDS_${PN} += " \
    qt3d \
    qt5compat \
    qtbase \
    qtcoap \
    qtconnectivity \
    qtdeclarative \
    qtgraphicaleffects \
    qtimageformats \
    qtmqtt \
    qtnetworkauth \
    qtquick3d \
    qtquickcontrols2 \
    qtquicktimeline \
    qtserialport \
    qtshadertools \
    qtspeech \
    qtsvg \
    qttools \
    qttranslations \
    qtvirtualkeyboard \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland', '', d)} \
    qtwebchannel \
    qtwebsockets \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'qtx11extras', '', d)} \
"
