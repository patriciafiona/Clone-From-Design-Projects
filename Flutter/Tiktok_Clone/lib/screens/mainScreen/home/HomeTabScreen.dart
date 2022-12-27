import 'dart:async';

import 'package:flutter/material.dart';
import 'package:lottie/lottie.dart';
import 'package:tiktoklikescroller/tiktoklikescroller.dart';
import 'package:video_player/video_player.dart';

class HomeTabScreen extends StatelessWidget {
  const HomeTabScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final List<String> videos = <String>[
      "assets/videos/ssstik.io_1672128291462.mp4",
      "assets/videos/ssstik.io_1672128442058.mp4",
      "assets/videos/ssstik.io_1672128494400.mp4",
      "assets/videos/ssstik.io_1672128535310.mp4",
    ];

    return MaterialApp(
      home: HomeWidget(
        videos: videos, index: 0,
      ),
    );
  }
}

class HomeWidget extends StatefulWidget {
  HomeWidget({
    Key? key,
    required this.videos,
    this.testingController,
    required this.index
  }) : super(key: key);

  // This is a parameter to support testing in this repo
  final Controller? testingController;
  final List<String> videos;

  var index = 0;

  @override
  State<HomeWidget> createState() => _HomeWidgetState();
}

class _HomeWidgetState extends State<HomeWidget> {
  late Controller controller;
  late VideoPlayerController _videoController;

  var isLoading = false;

  @override
  initState() {
    controller = widget.testingController ?? Controller()
      ..addListener((event) {
        _handleCallbackEvent(event.direction, event.success);
      });

    _videoController = VideoPlayerController.asset(widget.videos[widget.index])
      ..initialize().then((_) {
        setState(() {
          _videoController.setLooping(true);
          _videoController.play();
        });
      });

    super.initState();
  }

  @override
  void dispose() {
    super.dispose();
    controller.disposeListeners();
    _videoController.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    if(isLoading == true) {
      return Scaffold(
        body: Container(
          width: (mediaQuery.size.width - mediaQuery.padding.horizontal),
          height: (mediaQuery.size.height - mediaQuery.padding.vertical),
          color: Colors.black,
          child: Center(
            child: Lottie.asset(
              'assets/lotties/tiktok-loader.json',
              repeat: true,
              width: (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.25,
            ),
          )
        ),
      );
    }else{
      return Scaffold(
        body: Container(
          color: Colors.black,
          child:
          TikTokStyleFullPageScroller(
            contentSize: widget.videos.length - widget.index,
            swipePositionThreshold: 0.2,
            swipeVelocityThreshold: 2000,
            animationDuration: const Duration(milliseconds: 50),
            controller: controller,
            builder: (BuildContext context, int index) {
              return SizedBox(
                width: (mediaQuery.size.width - mediaQuery.padding.horizontal),
                height: (mediaQuery.size.height - mediaQuery.padding.vertical),
                child: Stack(
                    children: [
                      Center(
                        key: Key('$index-text'),
                        child: _videoController.value.isInitialized
                            ? AspectRatio(
                          aspectRatio: _videoController.value.aspectRatio,
                          child: VideoPlayer(_videoController),
                        )
                            : Container(),
                      ),
                    ]),
              );
            },
          ),
        ),
      );
    }
  }

  void _handleCallbackEvent(ScrollDirection direction, ScrollSuccess success, {int? currentIndex}) {
    _videoController.seekTo(Duration.zero);
    _videoController.play();

    if(widget.index >= 0 && widget.index < widget.videos.length - 1) {
      setState(() {
        isLoading = true;
      });
    }

    Timer(const Duration(seconds: 1), () {
      setState(() {

        if(direction == ScrollDirection.BACKWARDS){
          if(widget.index > 0){
            widget.index = widget.index - 1;
          }
        }else if(direction == ScrollDirection.FORWARD){
          if(widget.index < widget.videos.length  ){
            widget.index = widget.index + 1;
          }
        }

        print("INDEX: ${widget.index}");

        _videoController = VideoPlayerController.asset(widget.videos[widget.index])
          ..initialize().then((_) {
            setState(() {
              _videoController.setLooping(true);
              _videoController.play();
            });
          });

        isLoading = false;
      });
    });
  }
}