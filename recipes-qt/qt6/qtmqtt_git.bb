LICENSE = "(GPL-3.0 & The-Qt-Company-GPL-Exception-1.0) | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

DEPENDS += "qtbase"

PACKAGECONFIG[examples] = "-DBUILD_EXAMPLES=ON,-DBUILD_EXAMPLES=OFF,qtdeclarative qtwebsockets"

SRCREV = "5ea20cc458733f7fb87cc1731791f3a94d5ea7d8"
