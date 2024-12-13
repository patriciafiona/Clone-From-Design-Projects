import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:simple_icons/simple_icons.dart';
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
                  return const Column(
                    children: [
                      Spacer(),

                      Text(
                        "My Furniture",
                        style: TextStyle(
                            color: Colors.white,
                            fontSize: 18,
                            fontFamily: "Lufga"
                        ),
                        textAlign: TextAlign.center,
                      ),
                      Text(
                        "Best Furniture in Town",
                        style: TextStyle(
                            color: Colors.white,
                            fontSize: 22,
                            fontFamily: "Lufga",
                            fontWeight: FontWeight.w600
                        ),
                        textAlign: TextAlign.center,
                      ),

                      Padding(
                        padding: EdgeInsets.symmetric(vertical: 16.0),
                        child: Divider(),
                      ),

                      Text(
                        "Plaza Indonesia",
                        style: TextStyle(
                            color: Colors.white,
                            fontSize: 14,
                            fontFamily: "Lufga",
                            fontWeight: FontWeight.bold
                        ),
                        textAlign: TextAlign.center,
                      ),
                      Text(
                        "Jl. M.H. Thamrin No.Kav.28-30, Gondangdia, Kec. Menteng, Kota Jakarta Pusat, Daerah Khusus Ibukota Jakarta 10350",
                        style: TextStyle(
                            color: Colors.white,
                            fontSize: 12,
                            fontFamily: "Lufga"
                        ),
                        textAlign: TextAlign.center,
                      ),

                      Spacer(),
                    ],
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
                                color: Colors.white,
                              fontFamily: "Lufga",
                            ),
                          ),
                          children: <CupertinoListTile>[
                            CupertinoListTile.notched(
                              title: const Text('Application'),
                              leading: Container(
                                width: double.infinity,
                                height: double.infinity,
                                decoration: BoxDecoration(
                                  color: CupertinoColors.activeBlue,
                                  borderRadius: BorderRadius.circular(8),
                                ),
                                child: const Icon(
                                  Icons.phone_android_outlined,
                                  color: Colors.white,
                                ),
                              ),
                              additionalInfo: const Text("Furniture App"),
                            ),
                            CupertinoListTile.notched(
                              title: const Text('Compatibility'),
                              leading: Container(
                                width: double.infinity,
                                height: double.infinity,
                                decoration: BoxDecoration(
                                  color: CupertinoColors.systemRed,
                                  borderRadius: BorderRadius.circular(8),
                                ),
                                child: const Icon(
                                  Icons.info_outline,
                                  color: Colors.white,
                                ),
                              ),
                              additionalInfo: const Text("Android, iOS"),
                            ),
                            CupertinoListTile.notched(
                              title: const Text('Technology'),
                              leading: Container(
                                width: double.infinity,
                                height: double.infinity,
                                decoration: BoxDecoration(
                                  color: CupertinoColors.activeOrange,
                                  borderRadius: BorderRadius.circular(8),
                                ),
                                child: const Icon(
                                  Icons.build_outlined,
                                  color: Colors.white,
                                ),
                              ),
                              additionalInfo: const Text("Flutter"),
                            ),
                            CupertinoListTile.notched(
                              title: const Text('Version'),
                              leading: Container(
                                width: double.infinity,
                                height: double.infinity,
                                decoration: BoxDecoration(
                                  color: CupertinoColors.systemPurple,
                                  borderRadius: BorderRadius.circular(8),
                                ),
                                child: const Icon(
                                  Icons.settings,
                                  color: Colors.white,
                                ),
                              ),
                              additionalInfo: const Text("v1.0.0"),
                            ),
                            CupertinoListTile.notched(
                              title: const Text('Developer'),
                              leading: Container(
                                width: double.infinity,
                                height: double.infinity,
                                decoration: BoxDecoration(
                                  color: CupertinoColors.systemTeal,
                                  borderRadius: BorderRadius.circular(8),
                                ),
                                child: const Icon(
                                  Icons.code,
                                  color: Colors.white,
                                ),
                              ),
                              additionalInfo: Text(developerName),
                            ),
                            CupertinoListTile.notched(
                              title: const Text('Designer'),
                              leading: Container(
                                width: double.infinity,
                                height: double.infinity,
                                decoration: BoxDecoration(
                                  color: CupertinoColors.activeGreen,
                                  borderRadius: BorderRadius.circular(8),
                                ),
                                child: const Icon(
                                  Icons.format_paint_outlined,
                                  color: Colors.white,
                                ),
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
                              color: Colors.white,
                              fontFamily: "Lufga",
                            ),
                          ),
                          children: <CupertinoListTile>[
                            CupertinoListTile.notched(
                              title: const Text('Github'),
                              leading: const SizedBox(
                                width: double.infinity,
                                height: double.infinity,
                                child: Icon(
                                    SimpleIcons.github,
                                    color: SimpleIconColors.github
                                ),
                              ),
                              trailing: const CupertinoListTileChevron(),
                              onTap: () => _launchURLOnBrowser('https://github.com/patriciafiona')
                            ),
                            CupertinoListTile.notched(
                              title: const Text('Youtube'),
                              leading: const SizedBox(
                                width: double.infinity,
                                height: double.infinity,
                                child: Icon(
                                    SimpleIcons.youtube,
                                    color: SimpleIconColors.youtube
                                ),
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
