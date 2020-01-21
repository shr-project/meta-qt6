LICENSE = "GPL-3.0 | LGPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv3;md5=c4fe8c6de4eef597feec6e90ed62e962 \
    file://LICENSE.GPL;md5=d32239bcb673463ab874e80d47fae504 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

DEPENDS += "qtbase qtdeclarative qtdeclarative-native qtquickcontrols2"

#PACKAGECONFIG ??= "sdl2"
PACKAGECONFIG[sdl2] = "-DFEATURE_sdl2=ON,-DFEATURE_sdl2=OFF,libsdl2"

SRCREV = "85d4181dd9786eab1837bd50880183b38934e0ee"
