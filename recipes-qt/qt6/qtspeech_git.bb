LICENSE = "GFDL-1.3 & BSD-3-Clause & (GPL-2.0-or-later | LGPL-3.0-only) | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://LICENSE.GPLv2;md5=05832301944453ec79e40ba3c3cfceec \
    file://LICENSE.LGPLv3;md5=c4fe8c6de4eef597feec6e90ed62e962 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

DEPENDS += "qtbase"

PACKAGECONFIG ?= "qml"
PACKAGECONFIG[flite] = "-DFEATURE_flite=ON,-DFEATURE_flite=OFF,flite qtmultimedia"
PACKAGECONFIG[qml] = ",,qtdeclarative qtdeclarative-native"
