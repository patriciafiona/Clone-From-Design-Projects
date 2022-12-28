import 'dart:async';

import 'package:flutter/material.dart';
import 'package:lottie/lottie.dart';
import 'package:readmore/readmore.dart';
import 'package:tiktok_clone/data/DataSource.dart';
import 'package:tiktok_clone/data/entity/VideoData.dart';
import 'package:tiktoklikescroller/tiktoklikescroller.dart';
import 'package:video_player/video_player.dart';
import 'package:marquee/marquee.dart';
import 'package:assets_audio_player/assets_audio_player.dart';

import '../../../utils/Utils.dart';

class HomeTabScreen extends StatelessWidget {
  const HomeTabScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final List<VideoData> videos = DataSource().allVideo;

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
  final List<VideoData> videos;

  var index = 0;

  @override
  State<HomeWidget> createState() => _HomeWidgetState();
}

class _HomeWidgetState extends State<HomeWidget> {
  late Controller controller;
  late VideoPlayerController _videoController;
  var textController = TextEditingController();

  AssetsAudioPlayer assetsAudioPlayer = AssetsAudioPlayer();

  var isLoading = false;

  @override
  initState() {
    controller = widget.testingController ?? Controller()
      ..addListener((event) {
        _handleCallbackEvent(event.direction, event.success);
      });

    _videoController = VideoPlayerController.asset("assets/videos/${widget.videos[widget.index].videoSource}")
      ..initialize().then((_) {
        setState(() {
          _videoController.setLooping(true);
          _videoController.play();
        });
      });

    assetsAudioPlayer = AssetsAudioPlayer();
    assetsAudioPlayer.open(
      Audio(
        "assets/songs/${widget.videos[widget.index].musicSource}",
      ),
      autoStart: true,
      showNotification: false,
    );

    super.initState();
  }

  @override
  void dispose() {
    super.dispose();
    controller.disposeListeners();
    _videoController.dispose();
    textController.dispose();
    assetsAudioPlayer.dispose();
  }

  void didChangeAppLifecycleState(AppLifecycleState state) {
    if (state == AppLifecycleState.paused) {
      assetsAudioPlayer.stop();
    }
  }

  Widget checkIsVerified(bool status) {
    if(status == true){
      return const Icon(
        Icons.verified,
        color: Colors.blue,
        size: 15,
      );
    }else{
      return const SizedBox();
    }
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

                      Positioned.fill(
                        child: Align(
                          alignment: Alignment.bottomCenter,
                          child: Container(
                            padding: const EdgeInsets.only(bottom: 30, left: 10, right: 10),
                            child: Row(
                              mainAxisAlignment: MainAxisAlignment.start,
                              children: [
                                Expanded(
                                  child: Column(
                                    mainAxisAlignment: MainAxisAlignment.end,
                                    crossAxisAlignment: CrossAxisAlignment.start,
                                    children: [
                                      Row(
                                        children: [
                                          Text(
                                            widget.videos[widget.index].username,
                                            style: const TextStyle(
                                                color: Colors.white,
                                                fontSize: 20,
                                                fontWeight: FontWeight.bold
                                            ),
                                          ),
                                          const SizedBox(width: 5),
                                          checkIsVerified(widget.videos[widget.index].isVerified)
                                        ],
                                      ),
                                      const SizedBox(height: 10),
                                      SizedBox(
                                        width: (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.7,
                                        child: ReadMoreText(
                                          widget.videos[widget.index].description,
                                          trimLines: 2,
                                          style: const TextStyle(color: Colors.white),
                                          colorClickableText: Colors.white38,
                                          trimMode: TrimMode.Line,
                                          trimCollapsedText: '...Show more',
                                          trimExpandedText: ' show less',
                                          moreStyle: const TextStyle(fontSize: 14, color: Colors.white),
                                        ),
                                      ),
                                      const SizedBox(height: 10),
                                      Row(
                                        children: [
                                          Container(
                                            margin: const EdgeInsets.only(right: 10),
                                            width: 15,
                                            height: 15,
                                            child: Image.asset(
                                                "assets/images/song_icon.png"
                                            )
                                          ),
                                          SizedBox(
                                            width: (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.5,
                                            height: 20,
                                            child: LayoutBuilder(builder: (context, size) {
                                              var span = TextSpan(
                                                text: widget.videos[widget.index].musicSource.split('.mp3')[0],
                                                style: const TextStyle(
                                                  fontWeight: FontWeight.bold,
                                                  color: Colors.white,
                                                  fontSize: 14,
                                                )
                                              );

                                              // Use a textpainter to determine if it will exceed max lines
                                              var tp = TextPainter(
                                                maxLines: 1,
                                                textAlign: TextAlign.left,
                                                textDirection: TextDirection.ltr,
                                                text: span,
                                              );

                                              // trigger it to layout
                                              tp.layout(maxWidth: (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.5 );

                                              // whether the text overflowed or not
                                              var exceeded = tp.didExceedMaxLines;

                                              if(exceeded){
                                                return Marquee(
                                                  text: widget.videos[widget.index].musicSource.split('.mp3')[0],
                                                  style: const TextStyle(
                                                    fontWeight: FontWeight.bold,
                                                    color: Colors.white,
                                                    fontSize: 14,
                                                  ),
                                                  scrollAxis: Axis.horizontal,
                                                  crossAxisAlignment: CrossAxisAlignment.start,
                                                  blankSpace: 20.0,
                                                  velocity: 100.0,
                                                  accelerationDuration: const Duration(milliseconds: 2500),
                                                  accelerationCurve: Curves.linear,
                                                  decelerationDuration: const Duration(milliseconds: 2300),
                                                  decelerationCurve: Curves.easeOut,
                                                );
                                              }

                                              return Text(
                                                widget.videos[widget.index].musicSource.split('.mp3')[0],
                                                style: const TextStyle(
                                                  fontWeight: FontWeight.bold,
                                                  color: Colors.white,
                                                  fontSize: 14,
                                                ),
                                              );
                                            }),
                                          ),
                                        ],
                                      ),
                                    ],
                                  ),
                                ),
                                Column(
                                  mainAxisAlignment: MainAxisAlignment.end,
                                  crossAxisAlignment: CrossAxisAlignment.center,
                                  children: [
                                    SizedBox(
                                      width: 55,
                                      height: 65,
                                      child: Stack(
                                          children: [
                                            Container(
                                              width: 55.0,
                                              height: 55.0,
                                              decoration: BoxDecoration(
                                                color: Colors.black,
                                                image: DecorationImage(
                                                  image: AssetImage("assets/images/${widget.videos[widget.index].profileSource}"),
                                                  fit: BoxFit.cover,
                                                ),
                                                borderRadius: const BorderRadius.all( Radius.circular(50.0)),
                                                border: Border.all(
                                                  color: Colors.white,
                                                  width: 2.0,
                                                ),
                                              ),
                                            ),
                                            Positioned.fill(
                                              child: Align(
                                                alignment: Alignment.bottomCenter,
                                                child:
                                                  SizedBox(
                                                    width: 23,
                                                    height: 23,
                                                    child: RawMaterialButton(
                                                      onPressed: () {},
                                                      elevation: 2.0,
                                                      fillColor: const Color.fromRGBO(255, 43, 85, 1),
                                                      padding: const EdgeInsets.all(1.0),
                                                      shape: const CircleBorder(),
                                                      child: const Icon(
                                                        Icons.add,
                                                        size: 17.0,
                                                        color: Colors.white,
                                                      ),
                                                    ),
                                                  )
                                              )
                                            ),
                                          ]
                                      ),
                                    ),
                                    const SizedBox(height: 15),
                                    IconButton(
                                      onPressed: () {},
                                      icon: const Icon(Icons.favorite),
                                      color: Colors.white,
                                      iconSize: 40,
                                    ),
                                    Text(
                                      numberFormatToString(widget.videos[widget.index].totalLikes),
                                      style: const TextStyle(
                                        color: Colors.white,
                                        fontSize: 12,
                                        fontWeight: FontWeight.bold
                                      ),
                                    ),
                                    const SizedBox(height: 10),
                                    IconButton(
                                      onPressed: () {},
                                      icon: const Icon(Icons.comment),
                                      color: Colors.white,
                                      iconSize: 40,
                                    ),
                                    Text(
                                      numberFormatToString(widget.videos[widget.index].totalComment),
                                      style: const TextStyle(
                                          color: Colors.white,
                                          fontSize: 12,
                                          fontWeight: FontWeight.bold
                                      ),
                                    ),
                                    const SizedBox(height: 10),
                                    IconButton(
                                      onPressed: () {},
                                      icon: const Icon(Icons.bookmark),
                                      color: Colors.white,
                                      iconSize: 40,
                                    ),
                                    Text(
                                      numberFormatToString(widget.videos[widget.index].totalBookmark),
                                      style: const TextStyle(
                                          color: Colors.white,
                                          fontSize: 12,
                                          fontWeight: FontWeight.bold
                                      ),
                                    ),
                                    const SizedBox(height: 10),
                                    IconButton(
                                      onPressed: () {},
                                      icon: const Icon(Icons.reply),
                                      color: Colors.white,
                                      iconSize: 40,
                                    ),
                                    Text(
                                      numberFormatToString(widget.videos[widget.index].totalShare),
                                      style: const TextStyle(
                                          color: Colors.white,
                                          fontSize: 12,
                                          fontWeight: FontWeight.bold
                                      ),
                                    ),
                                    const SizedBox(height: 10),
                                  ],
                                )
                              ],
                            ),
                          )
                        )
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

        _videoController = VideoPlayerController.asset("assets/videos/${widget.videos[widget.index].videoSource}")
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