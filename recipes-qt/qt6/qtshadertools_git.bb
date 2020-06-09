LICENSE = "GFDL-1.3 & BSD & GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

SRC_URI += "\
    file://0001-Regenerate-CMakeLists.patch \
"

DEPENDS = "qtbase qtshadertools-native"

BBCLASSEXTEND =+ "native nativesdk"

SRCREV = "714331c8fe377d04ec43828fe03792609b8f006a"
