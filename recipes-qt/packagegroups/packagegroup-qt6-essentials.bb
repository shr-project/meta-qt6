DESCRIPTION = "Qt6 essential modules"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

RDEPENDS:${PN} += " \
    qtbase \
    qtdeclarative \
    qttools \
    qttranslations-qtbase \
    qttranslations-qtdeclarative \
    qttranslations-qtquickcontrols2 \
"
