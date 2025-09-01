import { Component, OnInit } from '@angular/core';
import { AssetClassService } from 'src/app/asset-classes.service';
import { AssetClass } from 'src/app/models/asset-class.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import jsPDF from 'jspdf';
import autoTable, { RowInput } from 'jspdf-autotable';

type SortableField = 'className' | 'subClassName' | 'risk';

@Component({
  selector: 'app-asset-classes',
  templateUrl: './asset-classes.component.html',
  styleUrls: ['./asset-classes.component.css']
})
export class AssetClassesComponent implements OnInit {
  assetClasses: AssetClass[] = [];
  filteredAssetClasses: AssetClass[] = [];
  loading = true;

  createForm!: FormGroup;
  showForm = false;

  isEditMode = false;
  selectedAssetId: number | null = null;

  searchTerm = '';
  sortField: SortableField = 'className';
  sortDirection: 'asc' | 'desc' = 'asc';

  assetClassOptions = [
    'Cash',
    'Mutual Funds',
    'Bonds',
    'Fixed Income',
    'REITs',
    'Commodities'
  ];

  constructor(private service: AssetClassService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.loadAssetClasses();
    this.initCreateForm();
  }

  loadAssetClasses(): void {
    this.service.getAll().subscribe({
      next: (data) => {
        this.assetClasses = data;
        this.applyFilters();
        this.loading = false;
      },
      error: () => {
        console.error('Failed to fetch asset classes');
        this.loading = false;
      }
    });
  }

  initCreateForm(): void {
    this.createForm = this.fb.group({
      id: [null, Validators.required],
      className: ['', Validators.required],
      subClassName: ['', Validators.required],
      description: [''],
      risk: ['', Validators.required],
      investmentHorizon: [''],
      subAssetDescription: ['']
    });
  }

  createAssetClass(): void {
    if (this.createForm.invalid) return;

    const asset: AssetClass = this.createForm.value;

    if (this.isEditMode && this.selectedAssetId !== null) {
      this.service.update(this.selectedAssetId, asset).subscribe({
        next: (updated) => {
          const index = this.assetClasses.findIndex(a => a.id === this.selectedAssetId);
          if (index !== -1) this.assetClasses[index] = updated;
          this.applyFilters();
          this.resetForm();
          alert('Asset Class updated successfully!');
        },
        error: (err) => {
          console.error('Update failed', err);
        }
      });
    } else {
      this.service.create(asset).subscribe({
        next: (created) => {
          this.assetClasses.push(created);
          this.applyFilters();
          this.resetForm();
          alert('Asset Class added successfully!');
        },
        error: (err) => {
          console.error('Create failed', err);
        }
      });
    }
  }

  startEdit(asset: AssetClass): void {
    this.isEditMode = true;
    this.showForm = true;
    this.selectedAssetId = asset.id;
    this.createForm.patchValue(asset);
  }

  deleteAsset(id: number | null): void {
    if (id === null || !confirm('Are you sure you want to delete this asset class?')) return;

    this.service.delete(id).subscribe({
      next: () => {
        this.assetClasses = this.assetClasses.filter(a => a.id !== id);
        this.applyFilters();
        alert('Asset Class deleted successfully!');
      },
      error: (err) => {
        console.error('Delete failed', err);
      }
    });
  }

  resetForm(): void {
    this.createForm.reset();
    this.showForm = false;
    this.isEditMode = false;
    this.selectedAssetId = null;
  }

  // toggleForm(): void {
  //   this.showForm = !this.showForm;
  //   this.resetForm();
  // }

  toggleForm():void {
    this.showForm = !this.showForm;
    if (!this.showForm) {
      this.isEditMode = false;
      // this.resetForm();
    }
    
  }
  

  applyFilters(): void {
    const term = this.searchTerm.trim().toLowerCase();

    this.filteredAssetClasses = this.assetClasses.filter(asset => {
      const className = asset.className?.toLowerCase() || '';
      const subClassName = asset.subClassName?.toLowerCase() || '';
      const description = asset.description?.toLowerCase() || '';
      const risk = asset.risk?.toLowerCase() || '';
      const horizon = asset.investmentHorizon?.toLowerCase() || '';
      const subDesc = asset.subAssetDescription?.toLowerCase() || '';

      return (
        className.includes(term) ||
        subClassName.includes(term) ||
        description.includes(term) ||
        risk.includes(term) ||
        horizon.includes(term) ||
        subDesc.includes(term)
      );
    });

    if (this.sortField) {
      this.filteredAssetClasses.sort((a, b) => {
        const valA = (a[this.sortField] ?? '').toString().toLowerCase();
        const valB = (b[this.sortField] ?? '').toString().toLowerCase();
        return this.sortDirection === 'asc'
          ? valA.localeCompare(valB)
          : valB.localeCompare(valA);
      });
    }
  }

  toggleSortDirection(): void {
    this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    this.applyFilters();
  }

  exportToPDF(): void {
    const doc = new jsPDF();

    autoTable(doc, {
      head: [['ID', 'Class Name', 'Sub-Class', 'Description', 'Risk', 'Horizon', 'Sub-Asset Description']],
      body: this.filteredAssetClasses.map(asset => [
        asset.id ?? '',
        asset.className ?? '',
        asset.subClassName ?? '',
        asset.description ?? '',
        asset.risk ?? '',
        asset.investmentHorizon ?? '',
        asset.subAssetDescription ?? ''
      ]) as RowInput[]
      
    });

    doc.save('asset-classes.pdf');
  }
}