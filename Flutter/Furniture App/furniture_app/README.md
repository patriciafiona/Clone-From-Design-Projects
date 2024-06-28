# furniture_app

A new Flutter project.

## Getting Started

This project is a starting point for a Flutter application.

A few resources to get you started if this is your first Flutter project:

- [Lab: Write your first Flutter app](https://docs.flutter.dev/get-started/codelab)
- [Cookbook: Useful Flutter samples](https://docs.flutter.dev/cookbook)

For help getting started with Flutter development, view the
[online documentation](https://docs.flutter.dev/), which offers tutorials,
samples, guidance on mobile development, and a full API reference.

## How to Generate api_helper.g.dart?
1. run clean all ->

flutter clean
flutter pub get
flutter packages upgrade
add this to your service ->

2. generate it all again by running this command in cmd->

flutter pub run build_runner build --delete-conflicting-outputs
