LICENSE = "GFDL-1.3 & BSD & GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

SRC_URI += " \
    git://github.com/assimp/assimp.git;name=assimp;branch=assimp_5.0_release;protocol=https;destsuffix=git/src/3rdparty/assimp/src \
"

DEPENDS = "qtbase qtdeclarative qtshadertools qtshadertools-native"

BBCLASSEXTEND =+ "native nativesdk"

PACKAGECONFIG ??= ""
PACKAGECONFIG[system-assimp] = "-DFEATURE_system-assimp=ON,-DFEATURE_system-assimp=OFF,assimp"

_FILES_${PN}-qmlplugins += " \
  ${OE_QMAKE_PATH_QML}/QtQuick3D/Helpers/meshes/*.mesh \
"

SRCREV_qtquick3d = "7b5e5df5fbb2163e4b18daff383f7420f33bb9fd"
SRCREV_assimp = "8f0c6b04b2257a520aaab38421b2e090204b69df"

SRCREV_FORMAT = "qtquick3d_assimp"
