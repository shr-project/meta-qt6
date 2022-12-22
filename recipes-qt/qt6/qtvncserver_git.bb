LICENSE = "The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = "file://src/vncserver/qvncserver.h;endline=2;md5=a18574b7b7525f98e1f60d531ed2d015"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc
include recipes-qt/qt6/qt6-commercial.inc

DEPENDS += "qtbase qtdeclarative qtdeclarative-native"

PACKAGECONFIG[examples] = "-DQT_BUILD_EXAMPLES=ON,-DQT_BUILD_EXAMPLES=OFF,qtwayland qtwayland-native"
