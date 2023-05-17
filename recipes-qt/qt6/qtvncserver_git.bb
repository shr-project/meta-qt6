LICENSE = "The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = "file://src/vncserver/qvncserver.h;endline=20;md5=580773df14a5ab6e35a84ea48f3df1f8"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc
include recipes-qt/qt6/qt6-commercial.inc

DEPENDS += "qtbase qtdeclarative qtdeclarative-native"

PACKAGECONFIG[examples] = "-DQT_BUILD_EXAMPLES=ON,-DQT_BUILD_EXAMPLES=OFF,qtwayland qtwayland-native"
PACKAGECONFIG[libtomcrypt] = ",,libtomcrypt"
