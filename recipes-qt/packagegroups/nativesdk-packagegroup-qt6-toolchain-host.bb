DESCRIPTION = "Qt6 development host packages"
LICENSE = "MIT"

inherit nativesdk packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

RDEPENDS_${PN} += " \
    nativesdk-make \
    nativesdk-cmake \
    nativesdk-ninja \
    nativesdk-perl-modules \
    nativesdk-qt3d-dev \
    nativesdk-qt3d-tools \
    nativesdk-qtbase-dev \
    nativesdk-qtbase-tools \
    nativesdk-qtdeclarative-dev \
    nativesdk-qtdeclarative-tools \
    nativesdk-qttools-dev \
    nativesdk-qttools-tools \
"
