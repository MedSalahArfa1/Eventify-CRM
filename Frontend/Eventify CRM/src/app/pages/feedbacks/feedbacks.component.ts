import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
    selector: 'app-feedbacks',
    standalone: true,
    imports: [
        CommonModule,
    ],
    template: `<p>feedbacks works!</p>`,
    styleUrls: ['./feedbacks.component.css'],
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class FeedbacksComponent { }
