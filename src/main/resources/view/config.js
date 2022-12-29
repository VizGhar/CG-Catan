import { GraphicEntityModule } from './entity-module/GraphicEntityModule.js';
import { TooltipModule } from './tooltip-module/TooltipModule.js';
import { EndScreenModule } from './endscreen-module/EndScreenModule.js';
import { ToggleModule } from './toggle-module/ToggleModule.js'

// List of viewer modules that you want to use in your game
export const modules = [
	GraphicEntityModule,
	TooltipModule,
	EndScreenModule,
	ToggleModule
];

export const playerColors = [
	'#FFB74D',
	'#AED581',
	'#64B5F6',
	'#E57373'
];

export const options = [
	ToggleModule.defineToggle({
		toggle: 'tooltips',
		title: 'SHOW TOOLTIPS',
		values: {
			'ON': true,
			'OFF': false
		},
		default: true
	})
]
