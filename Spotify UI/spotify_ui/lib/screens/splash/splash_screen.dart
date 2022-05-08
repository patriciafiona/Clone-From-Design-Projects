import 'dart:async';

import 'package:flutter/material.dart';
import 'package:spotify_ui/screens/main/main_screen.dart';

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
    _timer = Timer(const Duration(seconds: 3),
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
          color: Color.fromRGBO(14, 16, 12, 1.0)
        ),
        child: Center(
          child:
          Image.asset(
              'assets/images/spotify_logo_v4.png',
            fit: BoxFit.cover,
          ),
        ),
      ),
    );
  }
}

