DESCRIPTION = "Qt6 modules"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

RDEPENDS_${PN} += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qt3d', '', d)} \
    qt5compat \
    qtbase \
    qtcharts \
    qtcoap \
    qtconnectivity \
    qtdeclarative \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtgraphicaleffects', '', d)} \
    qtimageformats \
    qtmqtt \
    qtnetworkauth \
    qtopcua \
    qtquick3d \
    qtquickcontrols2 \
    qtquickdesigner-components \
    qtquicktimeline \
    qtremoteobjects \
    qtsensors \
    qtserialbus \
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
"
