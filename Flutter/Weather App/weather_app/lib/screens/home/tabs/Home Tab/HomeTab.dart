import 'dart:ffi';

import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

class HomeTab extends StatefulWidget {
  const HomeTab({super.key});

  @override
  State<HomeTab> createState() => _HomeTabState();
}

class _HomeTabState extends State<HomeTab> {
  String greeting() {
    var hour = DateTime.now().hour;
    if (hour > 6 &&hour < 12) {
      return 'Morning';
    }else if (hour == 12) {
      return 'Noon';
    }else if (hour > 13 && hour < 17) {
      return 'Afternoon';
    } else {
      return 'Night';
    }
  }

  @override
  Widget build(BuildContext context) {
    double screenWidth = MediaQuery.of(context).size.width;
    double screenHeight = MediaQuery.of(context).size.height;

    return Scaffold(
      body: Container(
        decoration: BoxDecoration(
          image: DecorationImage(
            image: AssetImage("assets/images/sceen_${greeting().toLowerCase()}.png"),
            fit: BoxFit.cover,
          ),
        ),
        child: Container(
          width: screenWidth,
          height: screenHeight,
          padding: const EdgeInsets.symmetric(vertical: 32, horizontal: 16),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                "Good ${greeting()}!",
                style: const TextStyle(
                  color: Colors.white,
                  fontSize: 20,
                  fontWeight: FontWeight.bold
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
