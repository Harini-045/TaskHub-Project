import { Component } from '@angular/core';
import { Team } from '../model/team.model';
import { Teamservice } from '../services/teamservice';
import { FormsModule } from '@angular/forms';
import { User } from '../model/user.model';
import { Userservice } from '../services/userservice';


@Component({
  selector: 'app-team-dashboard',
  imports: [FormsModule],
  templateUrl: './teamdashboard.html',
  styleUrls: ['./teamdashboard.css']
})
export class Teamdashboard {

  allTeams: Team[] = [];
  teamModalOpen = false;
  isEditMode = false;
  editTeamData: Team = {} as Team;

  showUsersModal = false;
  selectedTeam: Team | null = null;
  teamUsers: User[] = [];

  constructor(
    private teamService: Teamservice,
    private userService: Userservice
  ) {}

  ngOnInit() {
    this.loadTeams();
  }

  loadTeams() {
    this.teamService.getAllTeams().subscribe({
      next: (res) => (this.allTeams = res),
      error: (err) => console.error('Error loading teams:', err),
    });
  }

  openAddTeamModal() {
    this.isEditMode = false;
    this.editTeamData = {} as Team;
    this.teamModalOpen = true;
  }

  openEditTeamModal(team: Team) {
    this.isEditMode = true;
    this.editTeamData = { ...team };
    this.teamModalOpen = true;
  }

  closeTeamModal() {
    this.teamModalOpen = false;
    this.isEditMode = false;
    this.editTeamData = {} as Team;
  }

  saveTeam() {
    if (!this.editTeamData.teamName) {
      alert("Please enter team name!");
      return;
    }
    if (this.isEditMode) {
      this.teamService.updateATeam(this.editTeamData).subscribe({
        next: () => {
          this.loadTeams();
          this.closeTeamModal();
        },
        error: (err) => alert('Error updating team: ' + err)
      });
    } else {
      this.teamService.addATeam(this.editTeamData).subscribe({
        next: () => {
          this.loadTeams();
          this.closeTeamModal();
        },
        error: (err) => alert('Error adding team: ' + err)
      });
    }
  }

  deleteTeam(teamId: number) {
    this.teamService.deleteATeam(teamId).subscribe({
      next: () => this.loadTeams(),
      error: (err) => alert('Error deleting team: ' + err)
    });
  }

  viewUsers(teamId: number) {
    this.selectedTeam = this.allTeams.find((t) => t.teamId === teamId) ?? null;
    this.userService.getUsersByTeamId(teamId).subscribe({
      next: (users) => {
        this.teamUsers = users;
        this.showUsersModal = true;
      },
      error: (err) => alert('Error loading team users: ' + err)
    });
  }

  closeUsersModal() {
    this.showUsersModal = false;
    this.teamUsers = [];
    this.selectedTeam = null;
  }
}
