import 'package:flutter/material.dart';
import 'package:getwidget/getwidget.dart';

class ScaleAnimationImageWidget extends StatefulWidget {
  final String imagePath;
  final int duration;
  final double width;
  final double height;

  const ScaleAnimationImageWidget({
    super.key,
    required this.imagePath,
    required this.width,
    required this.height,
    required this.duration
  });

  @override
  State<ScaleAnimationImageWidget> createState() => _ScaleAnimationImageWidgetState();
}

class _ScaleAnimationImageWidgetState extends State<ScaleAnimationImageWidget> with TickerProviderStateMixin {

  late AnimationController controller;
  late Animation<double> animation;

  @override
  void initState() {
    super.initState();
    controller =
        AnimationController(duration: Duration(seconds: widget.duration), vsync: this);
    animation = CurvedAnimation(parent: controller, curve: Curves.linear);
    controller.repeat();
  }

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: widget.width,
      height: widget.height,
      child: GestureDetector(
        onTap: () {
          controller.repeat();
        },
        child: GFAnimation(
          scaleAnimation: animation,
          controller: controller,
          type: GFAnimationType.scaleTransition,
          child: Image.asset(
            widget.imagePath,
            width: 80,
            height: 80,
          ),
        ),
      ),
    );
  }
}
