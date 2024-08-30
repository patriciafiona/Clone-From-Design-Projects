import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';

import '../../../utils/Constants.dart';

enum AccountDetailType { general, others }

Map<AccountDetailType, Color> accountDetailTypes = <AccountDetailType, Color>{
  AccountDetailType.general: const Color(0xff191970),
  AccountDetailType.others: const Color(0xff40826d),
};

class UserTabScreen extends StatefulWidget {
  const UserTabScreen({super.key});

  @override
  State<UserTabScreen> createState() => _UserTabScreenState();
}

class _UserTabScreenState extends State<UserTabScreen> {
  AccountDetailType _selectedSegment = AccountDetailType.general;

  @override
  Widget build(BuildContext context) {
    double screenWidth = MediaQuery.sizeOf(context).width;
    double screenHeight = MediaQuery.sizeOf(context).height;

    _launchURLOnBrowser(String link) async {
      final Uri url = Uri.parse(link);
      if (!await launchUrl(url)) {
        throw Exception('Could not launch $url');
      }
    }

    DateTime now = DateTime.now();

    return Container(
      width: screenWidth,
      color: Colors.transparent,
      padding: const EdgeInsets.only(top: 58, left: 16, right: 16, bottom: 16),
      child: Column(
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              const SizedBox(
                width: 110,
                height: 110,
                child: CircleAvatar(
                  backgroundImage: AssetImage("assets/image/pp_patricia_fiona.jpeg"),
                ),
              ),
              const SizedBox(width: 16),
              Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    developerName,
                    style: const TextStyle(
                        color: Colors.white,
                        fontWeight: FontWeight.bold,
                        fontSize: 26
                    ),
                  ),
                  Text(
                    "@$developerUsername",
                    style: const TextStyle(
                        color: Colors.white,
                        fontSize: 14
                    ),
                  ),
                  const SizedBox(height: 8),
                  ElevatedButton(
                      onPressed: (){},
                      child: const Text("Edit Profile")
                  )
                ],
              )
            ],
          ),
          const SizedBox(height: 24),
          SizedBox(
            width: screenWidth,
            child: CupertinoSlidingSegmentedControl<AccountDetailType>(
              backgroundColor: CupertinoColors.lightBackgroundGray.withOpacity(.3),
              thumbColor: CupertinoColors.darkBackgroundGray,
              groupValue: _selectedSegment,
              onValueChanged: (AccountDetailType? value) {
                if (value != null) {
                  setState(() {
                    _selectedSegment = value;
                  });
                }
              },
              children: const <AccountDetailType, Widget>{
                AccountDetailType.general: Padding(
                  padding: EdgeInsets.symmetric(horizontal: 20),
                  child: Text(
                    'General',
                    style: TextStyle(color: CupertinoColors.white),
                  ),
                ),
                AccountDetailType.others: Padding(
                  padding: EdgeInsets.symmetric(horizontal: 20),
                  child: Text(
                    'Others',
                    style: TextStyle(color: CupertinoColors.white),
                  ),
                ),
              },
            ),
          ),
          SizedBox(
            height: screenHeight * 0.6,
            child: Builder(
              builder: (context) {
                if (_selectedSegment.name.toLowerCase() == "general") {
                  return Column(
                    children: [],
                  );
                }else{
                  return SingleChildScrollView(
                    child: Column(
                      children: [
                        CupertinoListSection.insetGrouped(
                          backgroundColor: Colors.transparent,
                          header: const Text(
                            'About the App',
                            style: TextStyle(
                                color: Colors.white
                            ),
                          ),
                          children: <CupertinoListTile>[
                            CupertinoListTile.notched(
                              title: const Text('Application'),
                              leading: Container(
                                width: double.infinity,
                                height: double.infinity,
                                color: CupertinoColors.systemRed,
                              ),
                              additionalInfo: const Text("Furniture App"),
                            ),
                            CupertinoListTile.notched(
                              title: const Text('Compatibility'),
                              leading: Container(
                                width: double.infinity,
                                height: double.infinity,
                                color: CupertinoColors.systemRed,
                              ),
                              additionalInfo: const Text("Android, iOS"),
                            ),
                            CupertinoListTile.notched(
                              title: const Text('Technology'),
                              leading: Container(
                                width: double.infinity,
                                height: double.infinity,
                                color: CupertinoColors.systemRed,
                              ),
                              additionalInfo: const Text("Flutter"),
                            ),
                            CupertinoListTile.notched(
                              title: const Text('Version'),
                              leading: Container(
                                width: double.infinity,
                                height: double.infinity,
                                color: CupertinoColors.systemRed,
                              ),
                              additionalInfo: const Text("v1.0.0"),
                            ),
                            CupertinoListTile.notched(
                              title: const Text('Developer'),
                              leading: Container(
                                width: double.infinity,
                                height: double.infinity,
                                color: CupertinoColors.systemRed,
                              ),
                              additionalInfo: Text(developerName),
                            ),
                            CupertinoListTile.notched(
                              title: const Text('Designer'),
                              leading: Container(
                                width: double.infinity,
                                height: double.infinity,
                                color: CupertinoColors.systemRed,
                              ),
                              additionalInfo: const Text("Naimujjaman - Dribbble"),
                            ),
                          ],
                        ),
                    
                        CupertinoListSection.insetGrouped(
                          backgroundColor: Colors.transparent,
                          header: const Text(
                            'My Social Media',
                            style: TextStyle(
                                color: Colors.white
                            ),
                          ),
                          children: <CupertinoListTile>[
                            CupertinoListTile.notched(
                              title: const Text('Github'),
                              leading: Container(
                                width: double.infinity,
                                height: double.infinity,
                                color: CupertinoColors.systemRed,
                              ),
                              trailing: const CupertinoListTileChevron(),
                              onTap: () => _launchURLOnBrowser('https://github.com/patriciafiona')
                            ),
                            CupertinoListTile.notched(
                              title: const Text('Youtube'),
                              leading: Container(
                                width: double.infinity,
                                height: double.infinity,
                                color: CupertinoColors.systemRed,
                              ),
                              trailing: const CupertinoListTileChevron(),
                              onTap: () => _launchURLOnBrowser('https://www.youtube.com/@patriciafiona')
                            ),
                          ],
                        ),
                      ],
                    ),
                  );
                }
              },
            ),
            // child: Center(
            //   child: Text(
            //     'Selected Segment: ${_selectedSegment.name}',
            //     style: const TextStyle(color: CupertinoColors.white),
            //   ),
            // ),
          ),
          const Spacer(),
          Text(
            "Copyright © ${now.year} Patricia Fiona\nAll rights reserved",
            style: const TextStyle(
              color: Colors.grey,
            ),
            textAlign: TextAlign.center,
          )
        ],
      ),
    );
  }
}
