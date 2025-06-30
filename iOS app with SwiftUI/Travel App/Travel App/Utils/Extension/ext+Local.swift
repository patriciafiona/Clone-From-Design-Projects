//
//  ext+Local.swift
//  Travel App
//
//  Created by FIONA Patricia on 30/06/25.
//

import Foundation

public extension Locale {
    
    private static let availableRegions: [Locale] = { Locale.availableIdentifiers.map { Locale(identifier: $0) } }()
    
    init?(isoCode: String, from: Locale = .autoupdatingCurrent) {
        guard let locale = from.locale(isoCode: isoCode) else { return nil }
        self = locale
    }
    
    func alpha2Code(from isoCode: String) -> String? {
        let regionName = localizedString(forRegionCode: isoCode) ?? ""
        return Self.availableRegions.first(where: { localizedString(forRegionCode: $0.regionCode ?? "") == regionName })?.regionCode
    }
    
    func locale(isoCode: String) -> Locale? {
        let alpha2Code = alpha2Code(from: isoCode)
        var matchingLocale: Locale?
        
        for region in Self.availableRegions {
            if region.regionCode == alpha2Code {
                if region.languageCode == languageCode {
                    return region
                } else if matchingLocale == nil {
                    matchingLocale = region
                }
            }
        }
        
        return matchingLocale
    }
}
