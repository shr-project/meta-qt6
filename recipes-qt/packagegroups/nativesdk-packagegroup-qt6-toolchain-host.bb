DESCRIPTION = "Qt6 development host packages"
LICENSE = "MIT"

inherit nativesdk packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

RDEPENDS_${PN} += " \
    nativesdk-make \
    nativesdk-cmake \
    nativesdk-ninja \
    nativesdk-qtbase-dev \
    nativesdk-qtbase-tools \
    nativesdk-qtdeclarative-dev \
    nativesdk-qtdeclarative-tools \
    nativesdk-qttools-dev \
    nativesdk-qttools-tools \
    nativesdk-qtwayland-dev \
    nativesdk-qtwayland-tools \
    nativesdk-perl-modules \
"
