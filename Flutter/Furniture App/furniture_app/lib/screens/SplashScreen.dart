import 'dart:async';

import 'package:flutter/material.dart';
import 'package:animated_text_kit/animated_text_kit.dart';
import 'package:flutter/services.dart';
import 'package:furniture_app/screens/GetStartedScreen.dart';
import 'package:furniture_app/screens/HomeScreen.dart';

class SplashScreen extends StatefulWidget {
  const SplashScreen({super.key});

  @override
  State<SplashScreen> createState() => _SplashscreenState();
}

class _SplashscreenState extends State<SplashScreen> {
  @override
  Widget build(BuildContext context) {
    double screenWidth = MediaQuery.of(context).size.width;
    double screenHeight = MediaQuery.of(context).size.height;

    Timer(const Duration(seconds: 3, milliseconds: 100),
            () =>
            Navigator.of(context).pushReplacement(MaterialPageRoute(
                builder: (BuildContext context) => const GetStartedScreen())));

    //Text Animation settings
    const colorizeColors = [
      Colors.white24,
      Colors.white70,
      Colors.white24,
    ];

    const colorizeTextStyle = TextStyle(
      fontSize: 36.0,
      fontFamily: 'Horizon',
    );

    //Set navigation bar color
    var mySystemTheme = SystemUiOverlayStyle.light.copyWith(systemNavigationBarColor: Colors.black);
    SystemChrome.setSystemUIOverlayStyle(mySystemTheme);

    return MaterialApp(
      home: Scaffold(
        backgroundColor: Colors.black,
        body: Center(
          child: Column(
            children: [
              const Spacer(),
              Image.asset(
                "assets/gif/app_logo.gif",
                width: screenWidth * 0.5,
              ),
              Container(
                width: screenWidth * 0.8,
                padding: const EdgeInsets.symmetric(vertical: 24.0),
                child: AnimatedTextKit(
                  animatedTexts: [
                    ColorizeAnimatedText(
                      'Furniture',
                      textStyle: colorizeTextStyle,
                      textAlign: TextAlign.center,
                      colors: colorizeColors,
                    ),
                  ],
                  isRepeatingAnimation: true,
                ),
              ),
              const Spacer(),
            ],
          ),
        ),
      ),
    );
  }
}

