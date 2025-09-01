import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { TermsComponent } from './pages/terms/terms.component';
import { PrivacyComponent } from './pages/privacy/privacy.component';
import { SupportComponent } from './pages/support/support.component';
import { PortfolioComponent } from './portfolio/portfolio.component';
import { HoldingsComponent } from './holdings/holdings.component';
import { HoldingComponent } from './holding/holding.component';
import { AssetClassesComponent } from './asset-classes/asset-classes.component';
import { SecurityListComponent } from './security-list/security-list.component';

const routes: Routes = [
  { path: 'holding/:id', component: HoldingComponent },
  {path: 'security-master', component:SecurityListComponent},
  { path: 'portfolio', component: PortfolioComponent },
  { path: 'holding/:portfolioId', component: HoldingsComponent },
  { path: 'asset-classes', component: AssetClassesComponent },
  { path: '', component: HomeComponent },
  { path: 'terms', component: TermsComponent },
  { path: 'privacy', component: PrivacyComponent },
  { path: 'support', component: SupportComponent },
  { path: '**', redirectTo: '' } // fallback route
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}