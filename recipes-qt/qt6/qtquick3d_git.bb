LICENSE = "GFDL-1.3 & BSD & GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

SRC_URI += " \
    git://github.com/assimp/assimp.git;name=assimp;branch=assimp_5.0_release;protocol=https;destsuffix=git/src/3rdparty/assimp/src \
    file://0001-Regenerate-CMakeLists.patch \
"

DEPENDS = "qtbase qtdeclarative qtshadertools qtshadertools-native"

BBCLASSEXTEND =+ "native nativesdk"

PACKAGECONFIG ??= ""
PACKAGECONFIG[system-assimp] = "-DFEATURE_system-assimp=ON,-DFEATURE_system-assimp=OFF,assimp"

_FILES_${PN}-qmlplugins += " \
  ${OE_QMAKE_PATH_QML}/QtQuick3D/Helpers/meshes/*.mesh \
"

SRCREV_qtquick3d = "d64c09c8669bd78bac2455f59b9a0d75f87204c7"
SRCREV_assimp = "4e5017df696cf92301e75b200927c8c0dbeeb56d"

SRCREV_FORMAT = "qtquick3d_assimp"
