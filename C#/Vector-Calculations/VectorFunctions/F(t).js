var t = [];
var x = [];
var y = [];
var z = [];
for (var i = 0; i < 25; ++i) {
    t.push(i / 10);
    x.push(Math.cos(t[i]));
    y.push(Math.sin(t[i]));
    z.push(2 * t[i]);
}
var trace1 = {
    x: t,
    y: x,
    name: 'x',
    type: 'scatter'
};
var trace2 = {
    x: t,
    y: y,
    name: 'y',
    type: 'scatter'
};
var trace3 = {
    x: t,
    y: z,
    name: 'z',
    type: 'scatter'
};
var data = [trace1, trace2, trace3];
var layout = {
    title: 'F(t) = (cos(t), sin(t), 2t)'
};
Plotly.newPlot('myDiv', data, layout);
