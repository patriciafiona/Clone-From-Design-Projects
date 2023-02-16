import 'dart:async';

import 'package:flutter/material.dart';
import 'package:tiktok_clone/screens/mainScreen/MainScreen.dart';

class SplashScreen extends StatefulWidget {
  const SplashScreen({Key? key}) : super(key: key);

  @override
  State<SplashScreen> createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {

  Timer? _timer;

  @override
  void initState() {
    super.initState();
    _timer = Timer(const Duration(seconds: 5),
            ()=>Navigator.pushReplacement(context,
            MaterialPageRoute(builder:
                (context) =>
            const MainScreen()
            )
        )
    );
  }

  @override
  void dispose() {
    _timer?.cancel();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        padding: const EdgeInsets.symmetric(vertical: 20, horizontal: 80),
        decoration: const BoxDecoration(
          color: Colors.black
        ),
        child: Center(
          child: Image.asset(
            "assets/images/tiktok_logo_animation.gif",
            height: 200,
            width: 200,
          ),
        ),
      ),
    );
  }
}
