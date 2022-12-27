import 'package:flutter/material.dart';
import 'package:tiktoklikescroller/tiktoklikescroller.dart';

class HomeTabScreen extends StatelessWidget {
  const HomeTabScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final List<Color> colors = <Color>[
      Colors.red,
      Colors.blue,
      Colors.green,
    ];

    return MaterialApp(
      home: HomeWidget(
        colors: colors,
      ),
    );
  }
}

class HomeWidget extends StatefulWidget {
  const HomeWidget({
    Key? key,
    required this.colors,
    this.testingController,
  }) : super(key: key);

  // This is a parameter to support testing in this repo
  final Controller? testingController;
  final List<Color> colors;

  @override
  State<HomeWidget> createState() => _HomeWidgetState();
}

class _HomeWidgetState extends State<HomeWidget> {
  late Controller controller;

  @override
  initState() {
    controller = widget.testingController ?? Controller()
      ..addListener((event) {
        _handleCallbackEvent(event.direction, event.success);
      });

    // controller.jumpToPosition(4);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    return Scaffold(
      body: TikTokStyleFullPageScroller(
        contentSize: widget.colors.length,
        swipePositionThreshold: 0.2,
        swipeVelocityThreshold: 2000,
        animationDuration: const Duration(milliseconds: 400),
        controller: controller,
        builder: (BuildContext context, int index) {
          return Container(
            width: (mediaQuery.size.width - mediaQuery.padding.horizontal),
            height: (mediaQuery.size.height - mediaQuery.padding.vertical),
            color: widget.colors[index],
            child: Stack(children: [
              Center(
                child: Text(
                  '$index',
                  key: Key('$index-text'),
                  style: const TextStyle(fontSize: 48, color: Colors.white),
                ),
              ),
            ]),
          );
        },
      ),
    );
  }

  void _handleCallbackEvent(ScrollDirection direction, ScrollSuccess success, {int? currentIndex}) {
      print("Scroll callback received with data: {direction: $direction, success: $success and index: ${currentIndex ?? 'not given'}}");
  }
}