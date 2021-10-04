Qt6 OpenEmbedded/Yocto Project layer
====================================

This layer depends on:

URI: https://git.openembedded.org/openembedded-core/

and optionally:

URI: https://git.openembedded.org/meta-openembedded/
URI: https://git.openembedded.org/meta-python2

Yocto version support
---------------------

The branching of meta-qt6 layer follows Qt branching scheme, that is
`dev` for the development branch, and `6.x` for each minor release.

Following table shows the Yocto branches which are used to test each
Qt version (x) and any additional Yocto release that are stated in
the LAYERSERIES_COMPAT (c).

| Yocto \ Qt | dev | 6.2 | 6.1 | 6.0 |
| ---------- |:---:|:---:|:---:|:---:|
| master     |  x  |  x  |     |     |
| hardknott  |  x  |  x  |  x  |  x  |
| gatesgarth |  c  |  x  |  x  |  x  |
| dunfell    |  x  |  x  |  x  |  x  |

QtWebEngine
-----------

QtWebEngine needs at least CMake 3.19, which is available starting from Hardknott.
For this reason QtWebEngine is not tested on older releases.

Contributing
------------

To contribute to this layer you should submit the patches for review using
[Qt Gerrit](https://codereview.qt-project.org).

More information about Qt Gerrit and how to use it:
 - https://wiki.qt.io/Gerrit_Introduction
 - https://wiki.qt.io/Setting_up_Gerrit

Layer maintainers
-----------------

 - Samuli Piippo <samuli.piippo@qt.io>

