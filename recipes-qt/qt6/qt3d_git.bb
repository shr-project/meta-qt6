LICENSE = "LGPL-3.0 | GPL-2.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv3;md5=8211fde12cc8a4e2477602f5953f5b71 \
    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
    file://LICENSE.GPL;md5=c96076271561b0e3785dad260634eaa8 \
"

inherit qt6-cmake
inherit features_check

REQUIRED_DISTRO_FEATURES = "opengl"

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

SRC_URI += " \
    ${QT_GIT}/${QT_GIT_PROJECT}/qtquick3d-assimp.git;name=assimp;branch=upstream/assimp_5.0_release;protocol=${QT_GIT_PROTOCOL};destsuffix=git/src/3rdparty/assimp/src \
"

DEPENDS = "qtbase qtdeclarative qtshadertools qtshadertools-native"

PACKAGECONFIG ??= ""
PACKAGECONFIG_class-target ?= "system-assimp"
PACKAGECONFIG[system-assimp] = "-DFEATURE_system_assimp=ON,-DQT_FEATURE_system_assimp=OFF,assimp"
PACKAGECONFIG[qtgamepad] = ",,qtgamepad"

SRCREV_FORMAT = "qt3d_assimp"
SRCREV_qt3d = "b2d0ef3f7ab36b93a51eb0bea14afe1e9fc5c0d1"
SRCREV_assimp = "4e5017df696cf92301e75b200927c8c0dbeeb56d"
