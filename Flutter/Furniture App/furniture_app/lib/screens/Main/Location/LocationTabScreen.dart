import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:url_launcher/url_launcher.dart';

import 'package:latlong2/latlong.dart';

class LocationTabScreen extends StatefulWidget {
  const LocationTabScreen({super.key});

  @override
  State<LocationTabScreen> createState() => _LocationTabScreenState();
}

class _LocationTabScreenState extends State<LocationTabScreen> {
  @override
  Widget build(BuildContext context) {
    double screenWidth = MediaQuery.sizeOf(context).width;
    double screenHeight = MediaQuery.sizeOf(context).height;

    var location = const LatLng(-6.193729, 106.821967);

    return Column(
      children: [
        Container(
          width: screenWidth,
          padding: const EdgeInsets.only(
              top: 36,
              left: 16,
              right: 16
          ),
          child: const Text(
            "Our Location",
            style: TextStyle(
                color: Colors.white,
                fontSize: 22,
                fontFamily: "Lufga",
                fontWeight: FontWeight.w600
            ),
          ),
        ),
        Container(
          width: screenWidth,
          height: screenHeight * 0.85,
          padding: const EdgeInsets.all(16),
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(36)
          ),
          child: FlutterMap(
            options: MapOptions(
              initialCenter: location, // Center the map over London
              initialZoom: 17.0,
            ),
            children: [
              TileLayer( // Display map tiles from any source
                urlTemplate: 'https://tile.openstreetmap.org/{z}/{x}/{y}.png', // OSMF's Tile Server
                userAgentPackageName: 'com.patriciafiona.funitureapp',
                // And many more recommended properties!
              ),
              MarkerLayer(
                markers: [
                  Marker(
                    point: location,
                    child: Container(
                      width: 80,
                      height: 80,
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(40),
                        color: Colors.amberAccent
                      ),
                      child: const Icon(
                        Icons.location_on,
                        color: Colors.black,
                      ),
                    ),
                  ),
                ],
              ),
              RichAttributionWidget( // Include a stylish prebuilt attribution widget that meets all requirments
                attributions: [
                  TextSourceAttribution(
                    'OpenStreetMap contributors',
                    onTap: () => launchUrl(Uri.parse('https://openstreetmap.org/copyright')), // (external)
                  ),
                  // Also add images...
                ],
              ),
            ],
          ),
        ),
      ],
    );
  }
}
