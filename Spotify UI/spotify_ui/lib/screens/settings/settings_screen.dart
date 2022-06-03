import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:spotify_ui/screens/auth/auth_screen.dart';

import '../../authentication/auth.dart';

class SettingsScreen extends StatefulWidget {
  static const routeName = '/settings_screen';

  @override
  State<SettingsScreen> createState() => _SettingsScreenState();
}

class _SettingsScreenState extends State<SettingsScreen> {
  bool _isDataSaver = false;
  bool _isGapless = false;
  bool _isAutomix = false;
  bool _isExplicitContent = false;
  bool _isShowUnplayable = false;
  bool _isNormalizeVolume = false;
  bool _isDeviceBroadcastStatusOn = false;
  bool _isAutoPlay = false;
  bool _isCanvas = false;

  @override
  initState() {
    _isDataSaver = false;
    _isGapless = false;
    _isAutomix = true;
    _isExplicitContent = true;
    _isShowUnplayable = false;
    _isNormalizeVolume = true;
    _isDeviceBroadcastStatusOn = false;
    _isAutoPlay = true;
    _isCanvas = false;
    super.initState();
  }

  double _currentCrossfadeValue = 12;

  List<String> dropdownVolumeLevel = [
    'Loud',
    'Normal',
    'Quite'
  ];
  String _selectedVolumeLevel = 'Normal';

  bool _isSigningOut = false;
  void _signOut(BuildContext context) async{
    setState(() {
      _isSigningOut = true;
    });

    await Authentication.signOut(context: context);

    setState(() {
      _isSigningOut = false;
    });

    Navigator.pushReplacementNamed(context, AuthScreen.routeName);
  }

  Widget _buildSwitchField(
      String title,
      String subtitle,
      bool selectedBool,
      Function logic
      ){
    return Row(
      children: [
        Expanded(child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              title,
              style: TextStyle(
                  fontFamily: 'Gotham',
                  fontWeight: FontWeight.w500,
                  fontSize: 16,
                  color: Colors.white
              ),
            ),
            Text(
              subtitle,
              style: TextStyle(
                  fontFamily: 'Gotham',
                  fontWeight: FontWeight.w300,
                  fontSize: 14,
                  color: Colors.grey
              ),
            )
          ],
        )),
        Switch(
          activeColor: Colors.green,
          value: selectedBool,
          onChanged: (x) => logic(x)
        ),
      ],
    );
  }

  Widget _buildDropDownField(
      String title,
      String subtitle,
      String selectedValue,
      List<String> options,
      Function logic
      ){
    return Row(
      children: [
        Expanded(child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              title,
              style: TextStyle(
                  fontFamily: 'Gotham',
                  fontWeight: FontWeight.w500,
                  fontSize: 16,
                  color: Colors.white
              ),
            ),
            Text(
              subtitle,
              style: TextStyle(
                  fontFamily: 'Gotham',
                  fontWeight: FontWeight.w300,
                  fontSize: 14,
                  color: Colors.grey
              ),
            )
          ],
        )),
        DropdownButton(
          value: selectedValue,
          items: options.map((String items) {
            return DropdownMenuItem(
              value: items,
              child: Text(items),
            );
          }).toList(),
          onChanged: (String? newValue) => logic(newValue),
          style: TextStyle(
              fontFamily: 'Gotham',
              fontWeight: FontWeight.w500,
              fontSize: 14,
              color: Colors.white
          ),
          dropdownColor: Colors.black38,
        ),
      ],
    );
  }

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    final FirebaseAuth auth = FirebaseAuth.instance;
    final User? user = auth.currentUser;

    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        elevation: 0,
        title: Text('Settings', style: Theme.of(context).textTheme.headline6, textAlign: TextAlign.center,),
      ),
      body: Container(
        decoration: const BoxDecoration(
          color: Colors.black87
        ),
        child: Container(
          width: double.infinity,
          child: SingleChildScrollView(
            padding: EdgeInsets.all(20),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Container(
                  width: double.infinity,
                  child: Text(
                    'Free Account',
                    textAlign: TextAlign.center,
                    style: Theme.of(context).textTheme.headline6,
                  ),
                ),
                SizedBox(height: 15,),
                Container(
                  alignment: Alignment.center,
                  child: ElevatedButton(
                    onPressed: (){},
                    child: const Text('GO PREMIUM'),
                    style: ElevatedButton.styleFrom(
                      primary: Colors.white,
                      onPrimary: Colors.black,
                      elevation : 0,
                      textStyle: const TextStyle(
                          fontFamily: 'Gotham',
                          fontWeight: FontWeight.bold,
                          fontSize: 18,
                      ),
                      minimumSize: Size(
                          (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.55, 50
                      ),
                      shape: const StadiumBorder(),
                    ),
                  ),
                ),
                SizedBox(height: 30,),
                Row(
                  children: [
                    user?.photoURL != null ?
                    CircleAvatar(
                      radius: 30,
                      backgroundImage: NetworkImage(
                        user?.photoURL as String
                      )
                    ):
                    CircleAvatar(
                      radius: 15,
                      child: Image.asset('assets/images/placeholder_avatar.jpeg'),
                    ),
                    SizedBox(
                      width: 10,
                    ),
                    Expanded(child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          user?.displayName != null ? user?.displayName as String : '???',
                          style: TextStyle(
                              fontFamily: 'Gotham',
                              fontWeight: FontWeight.bold,
                              fontSize: 18,
                              color: Colors.white
                          ),
                        ),
                        SizedBox(height: 5,),
                        Text(
                          'View Profile',
                          style: TextStyle(
                              fontFamily: 'Gotham',
                              fontWeight: FontWeight.bold,
                              fontSize: 14,
                              color: Colors.grey
                          ),
                        ),
                      ],
                    )),
                    Container(
                      child: TextButton(
                        child: Icon(Icons.arrow_forward_ios),
                        onPressed: () {},
                        style: TextButton.styleFrom(
                          primary: Colors.white,
                        ),
                      ),
                    ),
                  ],
                ),
                SizedBox(height: 20,),
                Text(
                  'Data Saver',
                  style: TextStyle(
                      fontFamily: 'Gotham',
                      fontWeight: FontWeight.bold,
                      fontSize: 20,
                      color: Colors.white
                  ),
                ),
                SizedBox(height: 20,),
                _buildSwitchField(
                  _isDataSaver ? 'On': 'Off',
                  'Sets your music quality to low and disables Canvas',
                  _isDataSaver,
                  (value) => setState(() {
                    _isDataSaver = value;
                  })
                ),
                SizedBox(height: 15,),
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      'Crossfade',
                      style: TextStyle(
                          fontFamily: 'Gotham',
                          fontWeight: FontWeight.w500,
                          fontSize: 16,
                          color: Colors.white
                      ),
                    ),
                    Text(
                      'Allows you to crossfade between songs',
                      style: TextStyle(
                          fontFamily: 'Gotham',
                          fontWeight: FontWeight.w300,
                          fontSize: 14,
                          color: Colors.grey
                      ),
                    )
                  ],
                ),
                Slider(
                  value: _currentCrossfadeValue,
                  max: 100,
                  divisions: 100,
                  activeColor: Colors.white,
                  inactiveColor: Colors.black26,
                  label: _currentCrossfadeValue.round().toString(),
                  onChanged: (double value) {
                    setState(() {
                      _currentCrossfadeValue = value;
                    });
                  },
                ),
                SizedBox(height: 15,),
                _buildSwitchField(
                  'Gapless',
                  'Allow gapless playback',
                  _isGapless,
                  (value) => setState(() {
                    _isGapless = value;
                  })
                ),
                SizedBox(height: 15,),
                _buildSwitchField(
                  'Automix',
                  'Allow smooth transitions between song in a playlist',
                  _isAutomix,
                  (value) => setState(() {
                    _isAutomix = value;
                  })
                ),
                SizedBox(height: 15,),
                _buildSwitchField(
                  'Allow Explicit Content',
                  'Turn on to play explicit content. Explicit content labeled with "E" tag.',
                  _isExplicitContent,
                  (value) => setState(() {
                    _isExplicitContent = value;
                  })
                ),
                SizedBox(height: 15,),
                _buildSwitchField(
                  'Show unplayable songs',
                  'Show songs that are unplayable.',
                  _isShowUnplayable,
                  (value) => setState(() {
                    _isShowUnplayable = value;
                  })
                ),
                SizedBox(height: 15,),
                _buildSwitchField(
                  'Normalize volume',
                  'Set the same volume level for all tracks',
                  _isNormalizeVolume,
                  (value) => setState(() {
                    _isNormalizeVolume = value;
                  })
                ),
                SizedBox(height: 15,),
                _buildDropDownField(
                  'Volume level',
                  'Adjust the volume for your environment',
                  _selectedVolumeLevel,
                  dropdownVolumeLevel,
                  (String? newValue){
                    setState(() {
                      _selectedVolumeLevel = newValue!;
                    });
                  }
                ),
                SizedBox(height: 15,),
                _buildSwitchField(
                    'Device Broadcast Status',
                    'Allow others app in your device to see what are you listening',
                    _isDeviceBroadcastStatusOn,
                        (value) => setState(() {
                          _isDeviceBroadcastStatusOn = value;
                    })
                ),
                SizedBox(height: 15,),
                _buildSwitchField(
                    'Autoplay',
                    'Keep on listening to similar tracks when music your end',
                    _isAutoPlay,
                        (value) => setState(() {
                          _isAutoPlay = value;
                    })
                ),
                SizedBox(height: 15,),
                _buildSwitchField(
                    'Canvas',
                    'Display shorts, looping visuals on tracks',
                    _isCanvas,
                        (value) => setState(() {
                          _isCanvas = value;
                    })
                ),
                SizedBox(height: 50,),
                Text(
                  'About',
                  textAlign: TextAlign.start,
                  style: TextStyle(
                      fontFamily: 'Gotham',
                      fontWeight: FontWeight.bold,
                      fontSize: 24,
                      color: Colors.white
                  ),
                ),
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      'Version',
                      style: TextStyle(
                          fontFamily: 'Gotham',
                          fontWeight: FontWeight.w300,
                          fontSize: 16,
                          color: Colors.grey
                      ),
                    ),
                    Text(
                      '8.5.25.894',
                      style: TextStyle(
                          fontFamily: 'Gotham',
                          fontWeight: FontWeight.w300,
                          fontSize: 14,
                          color: Colors.grey
                      ),
                    ),
                    SizedBox(height: 10,),
                    Container(
                      width: double.infinity,
                      child: TextButton(
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text(
                              'Log Out',
                              style: TextStyle(
                                  fontFamily: 'Gotham',
                                  fontWeight: FontWeight.bold,
                                  fontSize: 18,
                                  color: Colors.white
                              ),
                            ),
                            Text(
                              'You are log in as ${user?.displayName}',
                              style: TextStyle(
                                  fontFamily: 'Gotham',
                                  fontWeight: FontWeight.w500,
                                  fontSize: 14,
                                  color: Colors.grey
                              ),
                            )
                          ],
                        ),
                        onPressed: () => _signOut(context),
                        style: TextButton.styleFrom(
                          padding: EdgeInsets.zero,
                          primary: Colors.white,
                          tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                          alignment: Alignment.centerLeft,
                        ),
                      ),
                    ),
                  ],
                )
              ],
            )
          )
        )
      )
    );
  }
}
