DESCRIPTION = "Qt6 development host packages"
LICENSE = "MIT"

inherit nativesdk packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

RDEPENDS_${PN} += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', \
        'nativesdk-qtwayland-dev nativesdk-qtwayland-tools', '', d)} \
    nativesdk-qtbase-dev \
    nativesdk-qtbase-tools \
    nativesdk-qtdeclarative-dev \
    nativesdk-qtdeclarative-tools \
    nativesdk-qttools-dev \
    nativesdk-qttools-tools \
    nativesdk-perl-modules \
"
