LICENSE = "LGPL-3.0 | GPL-2.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv3;md5=8211fde12cc8a4e2477602f5953f5b71 \
    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
    file://LICENSE.GPL;md5=c96076271561b0e3785dad260634eaa8 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

SRC_URI += " \
    git://github.com/assimp/assimp.git;name=assimp;branch=assimp_5.0_release;protocol=https;destsuffix=git/src/3rdparty/assimp/src \
"

DEPENDS = "qtbase qtdeclarative qtshadertools-native"

PACKAGECONFIG ??= ""
PACKAGECONFIG_class-target ?= "system-assimp"
PACKAGECONFIG[system-assimp] = "-DFEATURE_system_assimp=ON,-DQT_FEATURE_system_assimp=OFF,assimp"
PACKAGECONFIG[qtgamepad] = ",,qtgamepad"

SRCREV_qt3d = "d4b5df43eae18b07d75e7da422ef635bbc636b7f"
SRCREV_assimp = "8840449226888801f64394fe8abe7f1b0372d499"
SRCREV_FORMAT = "qt3d_assimp"
