LICENSE = "GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

PACKAGE_ARCH = "all"

DEPENDS += "qttools-native"

PACKAGES_DYNAMIC = "${PN}-*"

SRC_URI += "\
    file://0001-Follow-INSTALL_TRANSLATIONSDIR-for-installations.patch \
"

python populate_packages_prepend () {
    do_split_packages(d, d.expand('${QT6_INSTALL_TRANSLATIONSDIR}'),
                      r'^(.*?)(?:_..)+\.qm$', d.expand('${PN}-%s'),
                      'Qt translations for %s', extra_depends='')
}

SRCREV = "7991a2b2b2fa1d54e2c582d1490e91614664fe84"
