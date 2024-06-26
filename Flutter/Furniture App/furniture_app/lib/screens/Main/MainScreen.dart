import 'dart:ui';

import 'package:curved_navigation_bar/curved_navigation_bar.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:furniture_app/screens/Main/Home/HomeTabScreen.dart';
import 'package:furniture_app/screens/Main/Location/LocationTabScreen.dart';
import 'package:furniture_app/screens/Main/Notification/NotificationTabScreen.dart';
import 'package:furniture_app/screens/Main/User/UserTabScreen.dart';

import '../../utils/Constants.dart';

class MainScreen extends StatefulWidget {
  MainScreen({super.key});

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  late DateTime currentBackPressTime;

  int _page = 0;
  GlobalKey<CurvedNavigationBarState> _bottomNavigationKey = GlobalKey();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        bottomNavigationBar: CurvedNavigationBar(
          backgroundColor: Colors.black,
          buttonBackgroundColor: yellowStabilo,
          key: _bottomNavigationKey,
          items: const <Widget>[
            Icon(Icons.house_outlined, size: 30),
            Icon(Icons.navigation_outlined, size: 30),
            Icon(Icons.notifications_outlined, size: 30),
            Icon(Icons.person_outlined, size: 30),
          ],
          onTap: (index) {
            setState(() {
              _page = index;
            });
          },
        ),
        body: Container(
          decoration: const BoxDecoration(
          image: DecorationImage(
            image: AssetImage("assets/image/background.png"),
            fit: BoxFit.cover,
            ),
          ),
          child: Builder(
            builder: (context) {
              switch(_page){
                case 0: return HomeTabScreen();
                case 1: return LocationTabScreen();
                case 2: return NotificationTabScreen();
                case 3: return UserTabScreen();
                default: return HomeTabScreen();
              }
            },
          )
        )
    );
  }

  Future<bool> onWillPop() {
    DateTime now = DateTime.now();
    if (currentBackPressTime == null ||
        now.difference(currentBackPressTime) > const Duration(seconds: 2)) {
      currentBackPressTime = now;
      Fluttertoast.showToast(msg: "Please back press again to exit");
      return Future.value(false);
    }
    return Future.value(true);
  }
}
