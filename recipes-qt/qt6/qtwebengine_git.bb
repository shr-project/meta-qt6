require recipes-qt/qt6/qtwebengine.inc
require recipes-qt/qt6/chromium-gn.inc

DEPENDS += " \
    nodejs-native \
    gperf-native \
    bison-native \
    qemu-native \
    nss nss-native \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'libxcomposite libxcursor libxi libxrandr libxtst libxkbfile', '', d)} \
    qtbase qtdeclarative \
    gn-native \
"

EXTRA_OECMAKE += "\
    -DFEATURE_qtwebengine_build=ON \
    -DFEATURE_qtpdf_build=OFF \
"

# chromium/third_party/openh264/BUILD.gn add -Wno-format to cflags
# causing following error, because -Wformat-security cannot be used together with -Wno-format
# cc1plus: error: -Wformat-security ignored without -Wformat [-Werror=format-security]
# http://errors.yoctoproject.org/Errors/Details/150333/
SECURITY_STRINGFORMAT = ""

PACKAGECONFIG ??= "\
    ${@bb.utils.filter('DISTRO_FEATURES', 'alsa pulseaudio', d)} \
    freetype \
    glib \
    harfbuzz \
    icu \
    libevent \
    libjpeg \
    libpci \
    libpng \
    libwebp \
    zlib \
    ${@bb.utils.contains('BBFILE_COLLECTIONS', 'openembedded-layer', '${PACKAGECONFIG_OPENEMBEDDED}', '', d)} \
    geolocation \
    webchannel \
    printing-and-pdf \
    pepper-plugins \
"
PACKAGECONFIG_OPENEMBEDDED ?= "\
    lcms2 \
    opus \
    snappy \
    webrtc \
    webrtc-pipewire \
"

PACKAGECONFIG[alsa] = "-DFEATURE_webengine_system_alsa=ON,-DFEATURE_webengine_system_alsa=OFF,alsa-lib"
PACKAGECONFIG[ffmpeg] = "-DFEATURE_webengine_system_ffmpeg=ON,-DFEATURE_webengine_system_ffmpeg=OFF,libav"
PACKAGECONFIG[freetype] = "-DFEATURE_webengine_system_freetype=ON,-DFEATURE_webengine_system_freetype=OFF,freetype"
PACKAGECONFIG[geolocation] = "-DFEATURE_webengine_geolocation=ON,-DFEATURE_webengine_geolocation=OFF,qtpositioning"
PACKAGECONFIG[glib] = "-DFEATURE_webengine_system_glib=ON,-DFEATURE_webengine_system_glib=OFF,glib-2.0"
PACKAGECONFIG[harfbuzz] = "-DFEATURE_webengine_system_harfbuzz=ON,-DFEATURE_webengine_system_harfbuzz=OFF,harfbuzz"
PACKAGECONFIG[icu] = "-DFEATURE_webengine_system_icu=ON,-DFEATURE_webengine_system_icu=OFF,icu"
PACKAGECONFIG[lcms2] = "-DFEATURE_webengine_system_lcms2=ON,-DFEATURE_webengine_system_lcms2=OFF,lcms"
PACKAGECONFIG[libevent] = "-DFEATURE_webengine_system_libevent=ON,-DFEATURE_webengine_system_libevent=OFF,libevent"
PACKAGECONFIG[libjpeg] = "-DFEATURE_webengine_system_libjpeg=ON,-DFEATURE_webengine_system_libjpeg=OFF,jpeg"
PACKAGECONFIG[libpng] = "-DFEATURE_webengine_system_libpng=ON,-DFEATURE_webengine_system_libpng=OFF,libpng"
PACKAGECONFIG[libvpx] = "-DFEATURE_webengine_system_libvpx=ON,-DFEATURE_webengine_system_libvpx=OFF,libvpx"
PACKAGECONFIG[libwebp] = "-DFEATURE_webengine_system_libwebp=ON,-DFEATURE_webengine_system_libwebp=OFF,libwebp"
PACKAGECONFIG[libxml] = "-DFEATURE_webengine_system_libxml=ON,-DFEATURE_webengine_system_libxml=OFF,libxml2 libxslt"
PACKAGECONFIG[minizip] = "-DFEATURE_webengine_system_minizip=ON,-DFEATURE_webengine_system_minizip=OFF,minizip"
PACKAGECONFIG[opus] = "-DFEATURE_webengine_system_opus=ON,-DFEATURE_webengine_system_opus=OFF,libopus"
PACKAGECONFIG[libpci] = "-DFEATURE_webengine_system_libpci=ON,-DFEATURE_webengine_system_libpci=OFF,pciutils"
PACKAGECONFIG[pepper-plugins] = "-DFEATURE_webengine_pepper_plugins=ON,-DFEATURE_webengine_pepper_plugins=OFF"
PACKAGECONFIG[printing-and-pdf] = "-DFEATURE_webengine_printing_and_pdf=ON,-DFEATURE_webengine_printing_and_pdf=OFF"
PACKAGECONFIG[proprietary-codecs] = "-DFEATURE_webengine_proprietary_codecs=ON,-DFEATURE_webengine_proprietary_codecs=OFF"
PACKAGECONFIG[pulseaudio] = "-DFEATURE_webengine_system_pulseaudio=ON,-DFEATURE_webengine_system_pulseaudio=OFF,pulseaudio"
PACKAGECONFIG[re2] = "-DFEATURE_webengine_system_re2=ON,-DFEATURE_webengine_system_re2=OFF,re2"
PACKAGECONFIG[snappy] = "-DFEATURE_webengine_system_snappy=ON,-DFEATURE_webengine_system_snappy=OFF,snappy"
PACKAGECONFIG[spellchecker] = "-DFEATURE_webengine_spellchecker=ON,-DFEATURE_webengine_spellchecker=OFF"
PACKAGECONFIG[webchannel] = "-DFEATURE_webengine_webchannel=ON,-DFEATURE_webengine_webchannel=OFF,qtwebchannel"
PACKAGECONFIG[webrtc] = "-DFEATURE_webengine_webrtc=ON,-DFEATURE_webengine_webrtc=OFF,libvpx"
PACKAGECONFIG[webrtc-pipewire] = "-DFEATURE_webengine_webrtc_pipewire=ON,-DFEATURE_webengine_webrtc_pipewire=OFF,pipewire glib-2.0"
PACKAGECONFIG[zlib] = "-DFEATURE_webengine_system_zlib=ON,-DFEATURE_webengine_system_zlib=OFF,zlib"

FILES:${PN} += "\
    ${QT6_INSTALL_TRANSLATIONSDIR} \
    ${QT6_INSTALL_DATADIR}/resources \
    ${QT6_INSTALL_LIBEXECDIR}/QtWebEngineProcess \
"
