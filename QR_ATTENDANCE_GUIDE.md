# QR Code Attendance System - User Guide

## Overview
The QR Code Attendance System allows staff members to check in and check out using unique QR codes scanned via webcam.

## Features

### 1. Automatic QR Code Generation
- Each staff member gets a unique QR code when added to the system
- QR code format: `STAFF:{ID}:{timestamp}`
- Example: `STAFF:S001:1709467200000`

### 2. Webcam QR Scanner
- Real-time QR code scanning using device camera
- Automatic detection and processing
- Works on desktop and mobile devices

### 3. Active Status Tracking
- **Active (🟢)**: Staff is currently checked in
- **Inactive (🔴)**: Staff is currently checked out
- Toggle status by scanning QR code

### 4. Attendance Logging
- Records last check-in time
- Records last check-out time
- Displays in real-time on attendance dashboard

## How to Use

### For Administrators

#### 1. Add New Staff
1. Go to `/staff/add`
2. Fill in staff details
3. Select position (determines staff type)
4. Submit form
5. System automatically generates unique QR code

#### 2. View Staff QR Code
1. Go to staff list `/staff`
2. Click "QR" button next to staff member
3. QR code is displayed
4. Options:
   - Print QR code
   - Download as PNG image
   - Give to staff member

#### 3. Scan QR Code
1. Go to `/staff/qr-scanner`
2. Click "Start Camera"
3. Allow camera access
4. Hold QR code in front of camera
5. System automatically:
   - Detects QR code
   - Identifies staff member
   - Toggles active status
   - Records timestamp

#### 4. View Attendance Report
1. Go to `/staff/attendance`
2. See real-time status of all staff
3. View check-in/check-out times
4. See active vs inactive counts

### For Staff Members

#### Check-In Process
1. Arrive at work
2. Go to QR scanner station
3. Show your QR code to camera
4. System displays: "✅ [Name] checked IN successfully!"
5. Status changes to Active (🟢)

#### Check-Out Process
1. End of shift
2. Go to QR scanner station
3. Show your QR code to camera
4. System displays: "✅ [Name] checked OUT successfully!"
5. Status changes to Inactive (🔴)

## System Flow

```
Staff Added → QR Code Generated
                    ↓
            QR Code Printed/Downloaded
                    ↓
            Staff Receives QR Code
                    ↓
        ┌───────────────────────┐
        │   Scan QR Code        │
        └───────────────────────┘
                    ↓
        ┌───────────────────────┐
        │  Check Current Status │
        └───────────────────────┘
                    ↓
        ┌───────────┴───────────┐
        │                       │
    Inactive?              Active?
        │                       │
    Check IN              Check OUT
        │                       │
    Set Active           Set Inactive
    Record Time          Record Time
        │                       │
        └───────────┬───────────┘
                    ↓
            Update Database
                    ↓
            Show Success Message
```

## Technical Details

### QR Code Data Structure
```
Format: STAFF:{staffId}:{timestamp}
Example: STAFF:S001:1709467200000

Components:
- Prefix: "STAFF:" (identifies as staff QR)
- Staff ID: Unique identifier (S001, N001, etc.)
- Timestamp: Generation time (prevents duplication)
```

### Staff Model Fields
```java
protected String qrCode;              // Unique QR code data
protected boolean isActive;           // Current status
protected LocalDateTime lastCheckIn;  // Last check-in time
protected LocalDateTime lastCheckOut; // Last check-out time
```

### Check-In/Check-Out Logic
```java
// Check In
public void checkIn() {
    this.isActive = true;
    this.lastCheckIn = LocalDateTime.now();
}

// Check Out
public void checkOut() {
    this.isActive = false;
    this.lastCheckOut = LocalDateTime.now();
}
```

## URLs

| Feature | URL | Description |
|---------|-----|-------------|
| Staff List | `/staff` | View all staff with status |
| Add Staff | `/staff/add` | Add new staff member |
| QR Scanner | `/staff/qr-scanner` | Scan QR codes via webcam |
| View QR Code | `/staff/qr-code/{id}` | View/print staff QR code |
| Attendance Report | `/staff/attendance` | Real-time attendance dashboard |

## Browser Requirements

### Camera Access
- Chrome 53+
- Firefox 36+
- Safari 11+
- Edge 12+

### Required Permissions
- Camera access (will prompt on first use)
- JavaScript enabled

## Troubleshooting

### Camera Not Working
1. Check browser permissions
2. Ensure HTTPS connection (required for camera)
3. Try different browser
4. Check if camera is being used by another app

### QR Code Not Scanning
1. Ensure good lighting
2. Hold QR code steady
3. Position QR code within camera view
4. Try moving closer/farther from camera
5. Ensure QR code is not damaged or blurry

### Invalid QR Code Error
1. Verify QR code belongs to registered staff
2. Check if QR code is from this system
3. Regenerate QR code if needed

## Security Features

1. **Unique QR Codes**: Each staff has unique, non-guessable QR code
2. **Timestamp Validation**: QR codes include generation timestamp
3. **Session Authentication**: Scanner requires admin login
4. **Real-time Validation**: System validates QR code against database

## Future Enhancements

- [ ] Attendance history/logs
- [ ] Export attendance reports (CSV/PDF)
- [ ] Geolocation verification
- [ ] Face recognition integration
- [ ] Mobile app for staff
- [ ] Email notifications
- [ ] Overtime tracking
- [ ] Shift scheduling integration

## Support

For issues or questions, contact system administrator.
