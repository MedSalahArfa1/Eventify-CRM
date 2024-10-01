import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vendor } from 'app/models/vendor.model';

@Injectable({
  providedIn: 'root'
})
export class VendorService {

  private apiUrl = 'http://localhost:8082/api/vendors'; // Replace with your backend URL if different

  constructor(private http: HttpClient) {}

  // Get all vendors
  getAllVendors(): Observable<Vendor[]> {
    return this.http.get<Vendor[]>(this.apiUrl);
  }

  // Get a vendor by ID
  getVendorById(id: number): Observable<Vendor> {
    return this.http.get<Vendor>(`${this.apiUrl}/${id}`);
  }

  // Create a new vendor
  createVendor(vendor: Vendor): Observable<Vendor> {
    return this.http.post<Vendor>(this.apiUrl, vendor);
  }

  // Update an existing vendor
  updateVendor(id: number, vendor: Vendor): Observable<Vendor> {
    return this.http.put<Vendor>(`${this.apiUrl}/${id}`, vendor);
  }

  // Delete a vendor by ID
  deleteVendor(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
