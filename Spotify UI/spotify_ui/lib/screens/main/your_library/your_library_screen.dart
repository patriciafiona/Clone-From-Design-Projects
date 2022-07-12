import 'package:flutter/material.dart';
import 'package:spotify_ui/screens/main/your_library/tab_content/music_tab_screen.dart';
import 'package:spotify_ui/screens/main/your_library/tab_content/podcast_tab_screen.dart';

class YourLibraryScreen extends StatefulWidget {
  static const routeName = '/your_library_screen';

  const YourLibraryScreen({Key? key}) : super(key: key);

  @override
  State<YourLibraryScreen> createState() => _YourLibraryScreenState();
}

class _YourLibraryScreenState extends State<YourLibraryScreen> with SingleTickerProviderStateMixin{
  late TabController _tabController;

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: 2, vsync: this);
    _tabController.addListener(_handleTabSelection);
  }

  void _handleTabSelection() {
    setState(() {
    });
  }

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    return Scaffold(
      body: Container(
        padding: EdgeInsets.all(20),
        width: double.infinity,
        color: Colors.black,
        child: SingleChildScrollView(
          child: Container(
            padding: EdgeInsets.only(top: 50, bottom: 100),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                TabBar(
                  isScrollable: true,
                  controller: _tabController,
                  unselectedLabelColor: Colors.grey,
                  labelStyle: TextStyle(fontSize: 32.0, fontWeight: FontWeight.bold),
                  unselectedLabelStyle: TextStyle(fontSize: 30.0, fontWeight: FontWeight.bold),
                  indicatorSize: TabBarIndicatorSize.label,
                  indicator: UnderlineTabIndicator(
                    borderSide: BorderSide(
                      width: 3, color: Colors.black,
                    ),
                  ),
                  tabs: [
                    Tab(
                      text: 'Music',
                    ),
                    Tab(
                      text: 'Podcasts',
                    ),
                  ],
                ),
                Container(
                  height: mediaQuery.size.height,
                  child: new TabBarView(
                    controller: _tabController,
                    children: <Widget>[
                      MusicTabScreen(),
                      PodcastTabScreen()
                    ],
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
