#!/usr/bin/env bash

set -e


function installSdk(){

#Use existing SDK with existing 'sdkmanager', otherwise install it
which sdkmanager &> /dev/null || getAndroidSdk

PROXY_ARGS=""
  if [[ ! -z "$HTTPS_PROXY" ]]; then
    PROXY_HOST="$(echo "$HTTPS_PROXY" | cut -d : -f 1,1)"
    PROXY_PORT="$(echo "$HTTPS_PROXY" | cut -d : -f 2,2)"
    PROXY_ARGS="--proxy=http --proxy_host=$PROXY_HOST --proxy_port=$PROXY_PORT"
  fi

  echo y | "$ANDROID_HOME/tools/bin/sdkmanager" $PROXY_ARGS "$@"
}

function getAndroidSdk {
  TMP=/tmp/sdk$$.zip
  download 'https://dl.google.com/android/repository/tools_r25.2.3-linux.zip' $TMP
  unzip -qod "$ANDROID_SDK" $TMP
  rm $TMP
}

function installAndroidSDK {
  export PATH="$ANDROID_HOME/platform-tools:$ANDROID_HOME/tools:$ANDROID_HOME/tools/bin:$PATH"

  mkdir -p "$ANDROID_HOME/licenses/"
  echo > "$ANDROID_HOME/licenses/android-sdk-license"
  echo -n 24333f8a63b6825ea9c5514f83c2829b004d1fee > "$ANDROID_HOME/licenses/android-sdk-license"

  installsdk 'platforms;android-29' 'cmake;3.6.4111459' 'build-tools;29.0.3'
}