//
//  TimezoneView.swift
//  iosApp
//
//  Created by Bolivar Cortes on 12/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TimezoneView: View {
    @EnvironmentObject private var timezoneItems: TimezoneItems
    private var timezoneHelper = TimeZoneHelperImpl()
    @State private var currentDate = Date()
    let timer = Timer.publish(every: 1000, on: .main, in: .common).autoconnect()
    @State private var showTimezoneDialog = false
    
    var body: some View {
        NavigationView {
          VStack {
            TimeCard(timezone: timezoneHelper.currentTimeZone(),
                     time: DateFormatter.short.string(from: currentDate),
                     date: DateFormatter.long.string(from: currentDate))
            Spacer()
              // 1
              List {
                // 2
                ForEach(Array(timezoneItems.selectedTimezones), id: \.self) { timezone in
                  // 3
                  NumberTimeCard(timezone: timezone,
                                 time: timezoneHelper.getTime(timezoneId: timezone),
                                 hours: "\(timezoneHelper.hoursFromTimeZone(otherTimeZoneId: timezone)) hours from local",
                                 date: timezoneHelper.getDate(timezoneId: timezone))
                      .withListModifier()
                } // ForEach
                // 4
                .onDelete(perform: deleteItems)
              } // List
              // 5
              .listStyle(.plain)
              Spacer()

              
              
          }
          .onReceive(timer) { input in
            currentDate = input
          }
          .navigationTitle("World Clocks")
            // 1
            .toolbar {
              // 2
              ToolbarItem(placement: .navigationBarTrailing) {
                // 3
                Button(action: {
                    showTimezoneDialog = true
                }) {
                    Image(systemName: "plus")
                        .frame(alignment: .trailing)
                        .foregroundColor(.black)
                }
              } // ToolbarItem
            } // toolbar

            
            
        } // NavigationView
        .fullScreenCover(isPresented: $showTimezoneDialog) {
          TimezoneDialog()
            .environmentObject(timezoneItems)
        }
    }
    
    func deleteItems(at offsets: IndexSet) {
        let timezoneArray = Array(timezoneItems.selectedTimezones)
        for index in offsets {
          let element = timezoneArray[index]
          timezoneItems.selectedTimezones.remove(element)
        }
    }
    
}

#Preview {
    TimezoneView()
}
