DESCRIPTION = "Qt6 development host packages"
LICENSE = "MIT"

inherit nativesdk packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

RDEPENDS_${PN} += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'nativesdk-qtwayland-tools', '', d)} \
    nativesdk-qtbase-tools \
    nativesdk-qtdeclarative-tools \
    nativesdk-qttools-tools \
    nativesdk-perl-modules \
"
