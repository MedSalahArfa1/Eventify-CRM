import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Vendor } from 'app/models/vendor.model';
import { VendorService } from 'app/services/vendor.service';

@Component({
  selector: 'app-vendors',
  templateUrl: './vendors.component.html',
  styleUrls: ['./vendors.component.css']
})
export class VendorsComponent implements OnInit {
  vendors: Vendor[] = []; // Stores all vendors
  filteredVendors: Vendor[] = []; // Stores filtered vendors for display
  currentVendor: Vendor | null = null; // Tracks the vendor being added or edited
  isAddingVendor: boolean = false; // Flag to determine if adding or editing
  searchQuery: string = ''; // Search query for filtering vendors

  // List of predefined service categories for event managers
  serviceCategories: string[] = [
    'Catering Services',
    'Decoration & Design',
    'Lighting & Sound',
    'Security Services',
    'Venue Management',
    'Transportation',
    'Photography & Videography',
    'Entertainment & Performers',
    'Event Planning & Coordination',
    'Equipment Rentals'
  ];

  constructor(
    private vendorService: VendorService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadVendors(); // Load the list of vendors when the component is initialized
  }

  // Fetch all vendors from the server
  loadVendors(): void {
    this.vendorService.getAllVendors().subscribe(vendors => {
      this.vendors = this.sortVendorsByName(vendors); // Sort vendors alphabetically by name
      this.filterVendors(); // Apply any existing search filters
    }, error => {
      console.error('Error loading vendors:', error);
    });
  }

  // Sort vendors alphabetically by their name
  sortVendorsByName(vendors: Vendor[]): Vendor[] {
    return vendors.sort((a, b) => a.vendorName.localeCompare(b.vendorName));
  }

  // Filter vendors based on the search query
  filterVendors(): void {
    if (!this.searchQuery.trim()) {
      this.filteredVendors = this.vendors; // If no search query, show all vendors
    } else {
      const query = this.searchQuery.toLowerCase();
      this.filteredVendors = this.vendors.filter(vendor => 
        vendor.vendorName.toLowerCase().includes(query) || 
        vendor.companyName.toLowerCase().includes(query) || 
        vendor.phone.toLowerCase().includes(query) || 
        vendor.email.toLowerCase().includes(query) || 
        vendor.serviceCategory.toLowerCase().includes(query)
      );
    }
  }

  // Open the form to add a new vendor
  openAddVendorCard(): void {
    this.currentVendor = {
      vendorId: null,
      vendorName: '',
      companyName: '',
      email: '',
      phone: '',
      serviceCategory: this.serviceCategories[0], // Set default to first category
      serviceDescription: ''
    };
    this.isAddingVendor = true;
  }

  // Open the form to edit an existing vendor
  openEditVendorCard(vendor: Vendor): void {
    if (vendor) {
      this.currentVendor = { ...vendor }; // Clone the vendor to edit
      this.isAddingVendor = false;
    } else {
      console.error('Vendor to edit is undefined');
    }
  }

  // Add a new vendor
  addVendor(): void {
    if (this.currentVendor) {
      this.vendorService.createVendor(this.currentVendor).subscribe(() => {
        this.resetForm();
        this.loadVendors(); // Refresh the vendor list
      }, error => {
        console.error('Error creating vendor:', error);
      });
    } else {
      console.error('Current vendor is undefined');
    }
  }

  // Update an existing vendor
  updateVendor(): void {
    if (this.currentVendor && this.currentVendor.vendorId) {
      this.vendorService.updateVendor(this.currentVendor.vendorId, this.currentVendor).subscribe(() => {
        this.resetForm();
        this.loadVendors(); // Refresh the vendor list
      }, error => {
        console.error('Error updating vendor:', error);
      });
    } else {
      console.error('Current vendor is undefined or missing vendorId');
    }
  }

  // Delete a vendor by ID
  deleteVendor(vendorId: number | null): void {
    if (confirm("Are you sure you want to delete this vendor?")) {
      this.vendorService.deleteVendor(vendorId).subscribe(() => {
        this.loadVendors(); // Refresh the vendor list
      }, error => {
        console.error('Error deleting vendor:', error);
      });
    }
  }

  // Reset the form and close the add/edit vendor card
  resetForm(): void {
    this.currentVendor = null;
    this.isAddingVendor = false;
  }
}
