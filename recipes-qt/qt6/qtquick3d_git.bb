LICENSE = "BSD-3-Clause & ( GFDL-1.3 & BSD & GPL-3.0 | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://src/3rdparty/assimp/src/LICENSE;md5=d9d5275cab4fb13ae624d42ce64865de \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

ASSIMP_BRANCH = "qt6_assimp"

SRC_URI += " \
    ${QT_GIT}/${QT_GIT_PROJECT}/qtquick3d-assimp.git;name=assimp;branch=${ASSIMP_BRANCH};protocol=${QT_GIT_PROTOCOL};destsuffix=git/src/3rdparty/assimp/src \
"

DEPENDS = "qtbase qtdeclarative qtshadertools qtshadertools-native qtquick3d-native"

BBCLASSEXTEND = "native nativesdk"

PACKAGECONFIG[system-assimp] = "-DFEATURE_system_assimp=ON,-DFEATURE_system_assimp=OFF,assimp"

FILES_${PN}-qmlplugins += " \
  ${QT6_INSTALL_QMLDIR}/QtQuick3D/Helpers/meshes/*.mesh \
"

SRCREV_FORMAT = "qtquick3d_assimp"
SRCREV_qtquick3d = "a324e5bbfe524e5ba21b9c20ccdceec185807a89"
SRCREV_assimp = "5a38cd0a03015ceabbd5bc6efb0730dde1ef74e5"
