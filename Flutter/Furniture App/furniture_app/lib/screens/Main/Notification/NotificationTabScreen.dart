import 'package:flutter/material.dart';

class NotificationTabScreen extends StatefulWidget {
  const NotificationTabScreen({super.key});

  @override
  State<NotificationTabScreen> createState() => _NotificationTabScreenState();
}

class _NotificationTabScreenState extends State<NotificationTabScreen> {
  @override
  Widget build(BuildContext context) {
    double screenWidth = MediaQuery.sizeOf(context).width;
    double screenHeight = MediaQuery.sizeOf(context).height;

    return Container(
      width: screenWidth,
      height: screenHeight,
      padding: const EdgeInsets.only(
        top: 36, left: 16, right: 16, bottom: 24
      ),
      child: Column(
        children: [
          const Spacer(),

          Image.asset(
            "assets/gif/empty_notif.gif",
            width: screenWidth * 0.5,
          ),
          const Text(
            "No Notification Yet",
            style: TextStyle(
                color: Colors.white,
                fontSize: 32,
                fontFamily: "Lufga",
                fontWeight: FontWeight.w600
            ),
          ),
          const Text(
            "You have no notification right now.\nCome back later",
            style: TextStyle(
                color: Colors.grey,
                fontSize: 14,
                fontFamily: "Lufga",
            ),
            textAlign: TextAlign.center,
          ),

          const Spacer(),
        ],
      ),
    );
  }
}
