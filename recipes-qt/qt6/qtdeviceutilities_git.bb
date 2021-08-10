LICENSE = "GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = "file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504"

inherit qt6-cmake
include recipes-qt/qt6/qt6.inc
include recipes-qt/qt6/qt6-git.inc

DEPENDS = "qtbase qtdeclarative qtdeclarative-native qtvirtualkeyboard"
RDEPENDS:${PN} = "connman"

SRCREV = "c0c3066d25678a92bcb2932b6595d019e9613d27"
