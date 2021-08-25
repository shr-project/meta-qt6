LICENSE = "GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = "file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504"

inherit qt6-cmake
include recipes-qt/qt6/qt6.inc
include recipes-qt/qt6/qt6-git.inc

DEPENDS = "qtbase qtdeclarative qtdeclarative-native qtvirtualkeyboard"
RDEPENDS:${PN} = "connman"

SRCREV = "baf90c5dd2599cc1928bbf18eb878564655c975b"
